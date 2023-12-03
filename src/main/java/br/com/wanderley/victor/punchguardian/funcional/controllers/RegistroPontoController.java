package br.com.wanderley.victor.punchguardian.funcional.controllers;

import br.com.wanderley.victor.punchguardian.comum.models.dtos.MensagemRetornoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.EspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.services.RegistroPontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @GetMapping("")
    public ResponseEntity<EspelhoPontoDTO> espelhoPontoPeriodo(@RequestParam Integer idProfissional,
                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim){
        return ResponseEntity.ok(this.pontoService.espelhoDePonto(idProfissional, dataInicio, dataFim));
    }

    @PostMapping("/{idProfissional}")
    public ResponseEntity<MensagemRetornoDTO> marcarPonto(@PathVariable Integer idProfissional){
        return ResponseEntity.ok(pontoService.registrarPonto(idProfissional));
    }
}
