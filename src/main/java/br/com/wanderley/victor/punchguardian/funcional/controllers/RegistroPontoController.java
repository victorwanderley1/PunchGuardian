package br.com.wanderley.victor.punchguardian.funcional.controllers;

import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import br.com.wanderley.victor.punchguardian.funcional.services.RegistroPontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ponto")
public class RegistroPontoController {
    @Autowired
    RegistroPontoService pontoService;

    @GetMapping("/{idProfissional}")
    public ResponseEntity<List<RegistroPonto>> espelhoDePonto(@PathVariable Integer idProfissional){
        return ResponseEntity.ok(pontoService.espelhoDePonto(idProfissional));
    }

    @PostMapping("/{idProfissional}")
    public ResponseEntity<RegistroPonto> marcarPonto(@PathVariable Integer idProfissional){
        return ResponseEntity.ok(pontoService.registrarPonto(idProfissional));
    }
}
