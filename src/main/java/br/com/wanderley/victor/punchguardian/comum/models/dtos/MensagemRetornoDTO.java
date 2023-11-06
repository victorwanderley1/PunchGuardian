package br.com.wanderley.victor.punchguardian.comum.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MensagemRetornoDTO {
    private String mensagemRetorno;
}
