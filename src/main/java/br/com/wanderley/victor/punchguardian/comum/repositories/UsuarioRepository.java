package br.com.wanderley.victor.punchguardian.comum.repositories;

import br.com.wanderley.victor.punchguardian.comum.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Optional<Usuario> findByUsername(String username);
}
