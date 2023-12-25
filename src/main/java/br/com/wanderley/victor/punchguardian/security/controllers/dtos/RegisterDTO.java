package br.com.wanderley.victor.punchguardian.security.controllers.dtos;

public record RegisterDTO(String username, String senha, Integer idPessoa, String papel) {
}
