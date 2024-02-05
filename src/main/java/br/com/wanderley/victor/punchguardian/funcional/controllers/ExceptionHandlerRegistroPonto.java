package br.com.wanderley.victor.punchguardian.funcional.controllers;

import br.com.wanderley.victor.punchguardian.comum.models.dtos.MensagemRetornoDTO;
import br.com.wanderley.victor.punchguardian.funcional.exceptions.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerRegistroPonto {

    @ExceptionHandler(NegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<MensagemRetornoDTO> handleViolacaoRegraDeNegocio(NegocioException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MensagemRetornoDTO.builder().mensagemRetorno(e.getMessage()).build());
    }
}
