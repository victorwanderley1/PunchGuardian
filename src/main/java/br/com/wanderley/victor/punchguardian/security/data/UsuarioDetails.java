package br.com.wanderley.victor.punchguardian.security.data;

import br.com.wanderley.victor.punchguardian.comum.models.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

public class UsuarioDetails implements UserDetails {

    private final Optional<Usuario> usuarioOptional;

    public UsuarioDetails(Optional<Usuario> usuarioOptional) {
        this.usuarioOptional = usuarioOptional;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuarioOptional.orElse(new Usuario()).getPapeis();
    }

    @Override
    public String getPassword() {
        return usuarioOptional.orElse(new Usuario()).getSenha();
    }

    @Override
    public String getUsername() {
        return usuarioOptional.orElse(new Usuario()).getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return usuarioOptional.orElse(new Usuario()).getAtivo();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !usuarioOptional.orElse(new Usuario()).getAtivo();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuarioOptional.orElse(new Usuario()).getAtivo();
    }
}
