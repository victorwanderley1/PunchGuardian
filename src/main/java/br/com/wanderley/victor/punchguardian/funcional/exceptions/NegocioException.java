package br.com.wanderley.victor.punchguardian.funcional.exceptions;

public class NegocioException extends RuntimeException {
    public NegocioException(final String mensagem){
        super(mensagem);
    }
}
