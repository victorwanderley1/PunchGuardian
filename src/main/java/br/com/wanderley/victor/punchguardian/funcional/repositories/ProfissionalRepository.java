package br.com.wanderley.victor.punchguardian.funcional.repositories;

import br.com.wanderley.victor.punchguardian.funcional.models.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Integer> {
}
