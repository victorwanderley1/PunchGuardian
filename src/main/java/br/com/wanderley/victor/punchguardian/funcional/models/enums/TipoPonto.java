package br.com.wanderley.victor.punchguardian.funcional.models.enums;

public enum TipoPonto {
    ENTRADA("Entrada", 0),
    SAIDA("Saída", 1);

    private final String nome;
    private final Integer numero;

    TipoPonto(String nome, Integer numero){
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public Integer getNumero() {
        return numero;
    }
}
