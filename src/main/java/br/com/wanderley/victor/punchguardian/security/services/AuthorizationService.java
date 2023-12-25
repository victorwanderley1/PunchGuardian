package br.com.wanderley.victor.punchguardian.security.services;

import br.com.wanderley.victor.punchguardian.comum.models.entities.Usuario;
import br.com.wanderley.victor.punchguardian.comum.repositories.UsuarioRepository;
import br.com.wanderley.victor.punchguardian.security.data.UsuarioDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public AuthorizationService(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optUsuario = repository.findByUsername(username);
        if(optUsuario.isEmpty())
            throw new UsernameNotFoundException("Usuário ["+username+"] não encontrado");

        return new UsuarioDetails(optUsuario);
    }


}
