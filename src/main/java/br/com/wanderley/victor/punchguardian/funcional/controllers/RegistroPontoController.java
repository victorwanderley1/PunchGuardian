package br.com.wanderley.victor.punchguardian.funcional.controllers;

import br.com.wanderley.victor.punchguardian.comum.models.dtos.MensagemRetornoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.RegistroPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.EspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.RegistroPontoEspelhoDTO;
import br.com.wanderley.victor.punchguardian.funcional.services.RegistroPontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ponto")
@CrossOrigin
public class RegistroPontoController {
    @Autowired
    RegistroPontoService pontoService;

    @GetMapping("/{idProfissional}")
    public EspelhoPontoDTO espelhoDePonto(@PathVariable Integer idProfissional){
        return pontoService.espelhoDePonto(idProfissional);
    }

    @PostMapping("/{idProfissional}")
    public ResponseEntity<MensagemRetornoDTO> marcarPonto(@PathVariable Integer idProfissional){
        return ResponseEntity.ok(pontoService.registrarPonto(idProfissional));
    }
}
