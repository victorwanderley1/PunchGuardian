package br.com.wanderley.victor.punchguardian.funcional.repositories;

import br.com.wanderley.victor.punchguardian.funcional.models.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}
