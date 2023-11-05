package br.com.wanderley.victor.punchguardian.funcional.services;

import br.com.wanderley.victor.punchguardian.funcional.models.Profissional;
import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.RegistroPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.enums.TipoPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.mappers.RegistroPontoMapper;
import br.com.wanderley.victor.punchguardian.funcional.repositories.ProfissionalRepository;
import br.com.wanderley.victor.punchguardian.funcional.repositories.RegistroPontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroPontoService {
    private final RegistroPontoRepository pontoRepository;
    private final ProfissionalRepository profissionalRepository;

    @Autowired
    public RegistroPontoService(RegistroPontoRepository pontoRepository, ProfissionalRepository profissionalRepository){
        this.pontoRepository = pontoRepository;
        this.profissionalRepository = profissionalRepository;
    }

    public RegistroPontoDTO registrarPonto(final Integer idProfissional) {
        Optional<Profissional> profissionalOptional = this.profissionalRepository.findById(idProfissional);
        if (profissionalOptional.isPresent()) {
            Profissional profissional = profissionalOptional.get();
            RegistroPonto ponto = new RegistroPonto(profissional, LocalDateTime.now(), getTipoPontoCorreto(profissional));
            return RegistroPontoMapper.toDTO(this.pontoRepository.save(ponto));
        }else
            throw new RuntimeException("Id do Profissional não existe");
    }

    private TipoPonto getTipoPontoCorreto(Profissional profissional){
        RegistroPonto ponto = pontoRepository.getTopByProfissionalOrderByHoraDesc(profissional);
        if (ponto != null && ponto.getTipoPonto() == TipoPonto.ENTRADA)
            return TipoPonto.SAIDA;
        else
            return TipoPonto.ENTRADA;
    }

    public List<RegistroPontoDTO> espelhoDePonto(final Integer idProfissional){
        Optional<Profissional> profissionalOptional = this.profissionalRepository.findById(idProfissional);
        if (profissionalOptional.isPresent()) {
            return RegistroPontoMapper.toListDTO(this.pontoRepository.findByProfissional(profissionalOptional.get()));
        }else
            throw new RuntimeException(String.format("Id Profissional %s não existe", idProfissional));
    }
}
