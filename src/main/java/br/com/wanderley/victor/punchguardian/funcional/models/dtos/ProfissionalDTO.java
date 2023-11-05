package br.com.wanderley.victor.punchguardian.funcional.models.dtos;

import br.com.wanderley.victor.punchguardian.comum.models.dtos.PessoaDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfissionalDTO {

    private Integer id;
    private CargoDTO cargo;
    private Integer cargaHorariaSemanal;
    private PessoaDTO pessoa;
}
