package br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho;

import br.com.wanderley.victor.punchguardian.funcional.models.dtos.CargoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalEspelhoPontoDTO {
    private String nome;
    private CargoDTO cargo;
}
