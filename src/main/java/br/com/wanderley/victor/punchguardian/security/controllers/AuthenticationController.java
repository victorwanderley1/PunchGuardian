package br.com.wanderley.victor.punchguardian.security.controllers;

import br.com.wanderley.victor.punchguardian.comum.models.entities.Papel;
import br.com.wanderley.victor.punchguardian.comum.models.entities.Usuario;
import br.com.wanderley.victor.punchguardian.comum.repositories.PapelRepository;
import br.com.wanderley.victor.punchguardian.comum.repositories.UsuarioRepository;
import br.com.wanderley.victor.punchguardian.security.controllers.dtos.AuthenticationDTO;
import br.com.wanderley.victor.punchguardian.security.controllers.dtos.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;

    private final PapelRepository papelRepository;
    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, PapelRepository papelRepository) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.papelRepository = papelRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegisterDTO data){
        if(this.usuarioRepository.findByUsername(data.username()).isPresent())
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        List<Papel> papeis = papelRepository.findByNome(data.papel()).orElse(new ArrayList<>());
        Usuario novoUsuario = new Usuario(data.idPessoa(), data.username(), encryptedPassword, papeis, LocalDate.now());

        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }

}
