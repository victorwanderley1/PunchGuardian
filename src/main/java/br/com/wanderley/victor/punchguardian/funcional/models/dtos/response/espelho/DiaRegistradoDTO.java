package br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaRegistradoDTO {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private List<RegistroPontoEspelhoDTO> pontos;
    @JsonFormat(locale = "US", pattern = "HH:mm:ss")
    private LocalTime totalHorasRegistradas;
}
