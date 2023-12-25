package br.com.wanderley.victor.punchguardian.comum.repositories;

import br.com.wanderley.victor.punchguardian.comum.models.entities.Papel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Integer> {

    Optional<Papel> findByNome(String nome);
}
