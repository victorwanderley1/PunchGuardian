package br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho;

import br.com.wanderley.victor.punchguardian.funcional.models.enums.TipoPonto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistroPontoEspelhoDTO {
    private Long id;
    @JsonFormat(locale = "US", pattern = "HH:mm:ss")
    private LocalDateTime hora;
    private TipoPonto tipoPonto;
    private Boolean correcao;
}
