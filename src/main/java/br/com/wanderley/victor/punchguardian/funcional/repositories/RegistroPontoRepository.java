package br.com.wanderley.victor.punchguardian.funcional.repositories;

import br.com.wanderley.victor.punchguardian.funcional.models.Profissional;
import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long> {

    List<RegistroPonto> findByProfissionalOrderByHoraAsc(Profissional profissional);
    List<RegistroPonto> findByProfissionalAndHoraBetweenOrderByHoraAsc(Profissional profissional, LocalDateTime dtInicio,
                                                                       LocalDateTime dtFim);
    RegistroPonto getTopByProfissionalOrderByHoraDesc(Profissional profissional);
}
