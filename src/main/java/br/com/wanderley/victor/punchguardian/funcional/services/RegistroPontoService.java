package br.com.wanderley.victor.punchguardian.funcional.services;

import br.com.wanderley.victor.punchguardian.comum.models.dtos.MensagemRetornoDTO;
import br.com.wanderley.victor.punchguardian.funcional.exceptions.EspelhoPontoException;
import br.com.wanderley.victor.punchguardian.funcional.exceptions.NegocioException;
import br.com.wanderley.victor.punchguardian.funcional.models.Profissional;
import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.RegistroPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.RegistroPontoRequestDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.EspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.enums.TipoPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.mappers.EspelhoPontoMapper;
import br.com.wanderley.victor.punchguardian.funcional.models.mappers.RegistroPontoMapper;
import br.com.wanderley.victor.punchguardian.funcional.repositories.ProfissionalRepository;
import br.com.wanderley.victor.punchguardian.funcional.repositories.RegistroPontoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.wanderley.victor.punchguardian.funcional.models.mappers.RegistroPontoMapper.toEntity;

@Service
public class RegistroPontoService {
    private final RegistroPontoRepository pontoRepository;
    private final ProfissionalRepository profissionalRepository;

    @Autowired
    public RegistroPontoService(RegistroPontoRepository pontoRepository, ProfissionalRepository profissionalRepository){
        this.pontoRepository = pontoRepository;
        this.profissionalRepository = profissionalRepository;
    }

    public MensagemRetornoDTO registrarPonto(final Integer idProfissional) {
        Profissional profissional = this.seProfissionalNaoExisteThrowException(idProfissional);
        RegistroPonto ponto = new RegistroPonto(profissional, LocalDateTime.now(), this.getProximoTipoPonto(profissional));
        return RegistroPontoServiceAux.getInstance().gerarMensagemRegistroPonto(RegistroPontoMapper.toDTO(this.pontoRepository.save(ponto)));
    }

    private TipoPonto getProximoTipoPonto(Profissional profissional){
        RegistroPonto ponto = pontoRepository.getTopByProfissionalOrderByHoraDesc(profissional);
        if (ponto != null && ponto.getTipoPonto() == TipoPonto.ENTRADA)
            return TipoPonto.SAIDA;
        else
            return TipoPonto.ENTRADA;
    }

    public EspelhoPontoDTO espelhoDePonto(final Integer idProfissional){
        Profissional profissional = this.seProfissionalNaoExisteThrowException(idProfissional);
        List<RegistroPonto> pontos = this.pontoRepository.findByProfissionalOrderByHoraAsc(profissional);
        return EspelhoPontoMapper.toDTO(pontos.isEmpty() ? List.of(new RegistroPonto(profissional, null, null)) : pontos);
    }

    public EspelhoPontoDTO espelhoDePonto(final Integer idProfissional,
                                          final LocalDate dtInicio,
                                          final LocalDate dtFim){
        Profissional profissional = this.seProfissionalNaoExisteThrowException(idProfissional);
        if(dtFim.isBefore(dtInicio))
            throw new EspelhoPontoException("A data final é anterior a data inicial");
        List<RegistroPonto> pontos = this.pontoRepository.findByProfissionalAndHoraBetweenOrderByHoraAsc(profissional,
                dtInicio.atStartOfDay(), dtFim.plusDays(1L).atStartOfDay());
        if(pontos.isEmpty())
            return new EspelhoPontoDTO();
        return EspelhoPontoMapper.toDTO(pontos);
    }

    private Profissional seProfissionalNaoExisteThrowException(final Integer id){
        return this.profissionalRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException(Profissional.class, String.format("Id Profissional %s não existe", id)));
    }

    private RegistroPonto sePontoNaoExisteThrowException(final Long id){
        return this.pontoRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException(Profissional.class, String.format("Id Profissional %s não existe", id)));
    }

    public MensagemRetornoDTO updateRegistroPonto(final Integer idProfissional, final RegistroPontoRequestDTO pontoDTO){
        validarUpdatePonto(idProfissional, pontoDTO);
        pontoDTO.setCorrecao(true);
        RegistroPonto registroPonto = toEntity(pontoDTO);
        registroPonto.setProfissional(this.profissionalRepository.getReferenceById(idProfissional));
        this.pontoRepository.save(registroPonto);
        return MensagemRetornoDTO.builder().mensagemRetorno("Registro de Ponto alterado com sucesso.").build();
    }

    private void validarUpdatePonto(Integer idProfissional, RegistroPontoRequestDTO pontoDTO) {
        this.seProfissionalNaoExisteThrowException(idProfissional);
        this.sePontoNaoExisteThrowException(pontoDTO.getId());
    }

    public MensagemRetornoDTO createRegistroPontoRetroativo(final Integer idProfissional,
                                                            final RegistroPontoRequestDTO pontoDTO){
        Profissional profissional = this.seProfissionalNaoExisteThrowException(idProfissional);
        RegistroPonto ponto = toEntity(pontoDTO);
        ponto.setProfissional(profissional);
        if(RegistroPontoServiceAux.isPontoRetroativo(ponto))
            this.pontoRepository.save(ponto);
        else
            throw new NegocioException("O ponto não pôde ser cadastrado, pois não é retroativo.");
        return MensagemRetornoDTO.builder().mensagemRetorno("Ponto registrado com sucesso.").build();
    }

    public RegistroPontoDTO findRegistroPonto(final Integer idProfissional, final Long idPonto){
        this.seProfissionalNaoExisteThrowException(idProfissional);
        return RegistroPontoMapper.toDTO(this.pontoRepository.findById(idPonto).orElseThrow(() -> new ObjectNotFoundException("Registro ponto", idPonto)));
    }
}
