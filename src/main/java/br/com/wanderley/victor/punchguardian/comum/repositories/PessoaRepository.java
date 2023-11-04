package br.com.wanderley.victor.punchguardian.comum.repositories;

import br.com.wanderley.victor.punchguardian.comum.models.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
