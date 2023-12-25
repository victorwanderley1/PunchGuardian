package br.com.wanderley.victor.punchguardian.security.services;

import br.com.wanderley.victor.punchguardian.comum.models.entities.Papel;
import br.com.wanderley.victor.punchguardian.comum.models.entities.Usuario;
import br.com.wanderley.victor.punchguardian.comum.repositories.PapelRepository;
import br.com.wanderley.victor.punchguardian.comum.repositories.UsuarioRepository;
import br.com.wanderley.victor.punchguardian.security.controllers.dtos.AuthenticationDTO;
import br.com.wanderley.victor.punchguardian.security.controllers.dtos.RegisterDTO;
import br.com.wanderley.victor.punchguardian.security.controllers.dtos.ResponseDTO;
import br.com.wanderley.victor.punchguardian.security.data.UsuarioDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final PapelRepository papelRepository;

    public UsuarioService(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioRepository usuarioRepository, PapelRepository papelRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.papelRepository = papelRepository;
    }

    public ResponseDTO registrarUsuario(RegisterDTO data){
        if(this.usuarioRepository.findByUsername(data.username()).isPresent())
            return new ResponseDTO("Nome de usuário existente");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        List<Papel> papeis = new ArrayList<>();
        String[] arrPapeis = data.papel().split(",");
        for(String papel : arrPapeis){
            papeis.add(this.papelRepository.findByNome(papel).orElseThrow(() -> new RuntimeException("Papel inexistente: "+papel)));
        }
        Usuario novoUsuario = new Usuario(data.idPessoa(), data.username(), encryptedPassword, papeis, LocalDate.now());
        this.usuarioRepository.save(novoUsuario);
        return new ResponseDTO("Usuário "+novoUsuario.getUsername()+" cadastrado com sucesso.");
    }

    public String login(AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((UsuarioDetails) auth.getPrincipal());
    }
}
