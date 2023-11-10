package br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EspelhoPontoDTO {
    private ProfissionalEspelhoPontoDTO profissional;
    private List<DiaRegistradoDTO> dias;
}
