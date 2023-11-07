package br.com.wanderley.victor.punchguardian.funcional.models.dtos;

import br.com.wanderley.victor.punchguardian.funcional.models.enums.TipoPonto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistroPontoDTO {
    private Long id;
    @JsonFormat(locale = "US", pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime hora;
    private TipoPonto tipoPonto;
    private ProfissionalDTO profissional;
    private Boolean correcao;

}
