package br.com.wanderley.victor.punchguardian.funcional.controllers;

import br.com.wanderley.victor.punchguardian.comum.models.dtos.MensagemRetornoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.RegistroPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.RegistroPontoRequestDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.EspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.services.RegistroPontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/periodo")
    public ResponseEntity<EspelhoPontoDTO> espelhoPontoPeriodo(@RequestParam Integer idProfissional,
                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim){
        return ResponseEntity.ok(this.pontoService.espelhoDePonto(idProfissional, dataInicio, dataFim));
    }

    @PostMapping("/{idProfissional}")
    public ResponseEntity<MensagemRetornoDTO> marcarPonto(@PathVariable Integer idProfissional){
        return ResponseEntity.ok(pontoService.registrarPonto(idProfissional));
    }

    @PostMapping(value = "/retroativo/{idProfissional}", name = "Marcar Ponto Retroativo")
    public ResponseEntity<MensagemRetornoDTO> marcarPontoRetroativo(@PathVariable Integer idProfissional,
                                                                    @RequestBody RegistroPontoRequestDTO pontoDTO){
        MensagemRetornoDTO mensagemRetorno = this.pontoService.createRegistroPontoRetroativo(idProfissional, pontoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagemRetorno);
    }

    @GetMapping("/{idProfissional}/{idPonto}")
    public ResponseEntity<RegistroPontoDTO> findRegistroPonto(@PathVariable final Integer idProfissional, @PathVariable final Long idPonto){
        RegistroPontoDTO registroPonto = this.pontoService.findRegistroPonto(idProfissional, idPonto);
        return ResponseEntity.ok(registroPonto);
    }

    @PutMapping("/{idProfissional}")
    public ResponseEntity<MensagemRetornoDTO> updateRegistroPonto (@PathVariable final Integer idProfissional,
                                                                   @RequestBody RegistroPontoRequestDTO pontoDTO){
        MensagemRetornoDTO mensagemRetornoDTO = this.pontoService.updateRegistroPonto(idProfissional, pontoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagemRetornoDTO);
    }
}
