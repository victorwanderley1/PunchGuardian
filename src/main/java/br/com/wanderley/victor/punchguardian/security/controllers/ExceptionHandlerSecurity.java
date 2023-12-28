package br.com.wanderley.victor.punchguardian.security.controllers;

import br.com.wanderley.victor.punchguardian.security.controllers.dtos.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerSecurity {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity handleLoginFail(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseDTO(e.getMessage()));
    }

}
