package br.com.wanderley.victor.punchguardian.funcional.services;

import br.com.wanderley.victor.punchguardian.comum.models.dtos.MensagemRetornoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.Profissional;
import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.RegistroPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.EspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.RegistroPontoEspelhoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.enums.TipoPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.mappers.EspelhoPontoMapper;
import br.com.wanderley.victor.punchguardian.funcional.models.mappers.RegistroPontoMapper;
import br.com.wanderley.victor.punchguardian.funcional.repositories.ProfissionalRepository;
import br.com.wanderley.victor.punchguardian.funcional.repositories.RegistroPontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

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
        return EspelhoPontoMapper.toDTO(this.pontoRepository.findByProfissionalOrderByHoraAsc(profissional));
    }

    private Profissional seProfissionalNaoExisteThrowException(final Integer id){
        return this.profissionalRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(String.format("Id Profissional %s não existe", id)));
    }
}
