package br.com.wanderley.victor.punchguardian.funcional.services;

import br.com.wanderley.victor.punchguardian.funcional.models.Profissional;
import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.enums.TipoPonto;
import br.com.wanderley.victor.punchguardian.funcional.repositories.ProfissionalRepository;
import br.com.wanderley.victor.punchguardian.funcional.repositories.RegistroPontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroPontoService {
    private final RegistroPontoRepository pontoRepository;
    private final ProfissionalRepository profissionalRepository;

    @Autowired
    public RegistroPontoService(RegistroPontoRepository pontoRepository, ProfissionalRepository profissionalRepository){
        this.pontoRepository = pontoRepository;
        this.profissionalRepository = profissionalRepository;
    }

    public RegistroPonto registrarPonto(final Integer idProfissional){
        Profissional profissional = this.profissionalRepository.findById(idProfissional).get();
        RegistroPonto ponto = new RegistroPonto(profissional, LocalDateTime.now(), TipoPonto.ENTRADA);
        ponto = this.pontoRepository.save(ponto);
        return ponto;
    }

    public List<RegistroPonto> espelhoDePonto(final Integer idProfissional){
        Profissional profissional = this.profissionalRepository.findById(idProfissional).get();
        return this.pontoRepository.findByProfissional(profissional);
    }
}
