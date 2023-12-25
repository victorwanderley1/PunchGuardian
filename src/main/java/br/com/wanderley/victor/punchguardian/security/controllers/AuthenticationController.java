package br.com.wanderley.victor.punchguardian.security.controllers;

import br.com.wanderley.victor.punchguardian.security.controllers.dtos.AuthenticationDTO;
import br.com.wanderley.victor.punchguardian.security.controllers.dtos.LoginResponseDTO;
import br.com.wanderley.victor.punchguardian.security.controllers.dtos.RegisterDTO;
import br.com.wanderley.victor.punchguardian.security.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UsuarioService usuarioService;

    public AuthenticationController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        return ResponseEntity.ok().body(new LoginResponseDTO(this.usuarioService.login(data)));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegisterDTO data){
        return ResponseEntity.ok().body(this.usuarioService.registrarUsuario(data));
    }

}
