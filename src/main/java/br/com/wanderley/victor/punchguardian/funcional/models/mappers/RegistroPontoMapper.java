package br.com.wanderley.victor.punchguardian.funcional.models.mappers;

import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.RegistroPontoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegistroPontoMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static RegistroPontoDTO toDTO(final RegistroPonto ponto){
        return MODEL_MAPPER.map(ponto, RegistroPontoDTO.class);
    }

    public static RegistroPonto toEntity(final RegistroPontoDTO pontoDTO){
        return MODEL_MAPPER.map(pontoDTO, RegistroPonto.class);
    }

    public static List<RegistroPontoDTO> toListDTO(final List<RegistroPonto> pontos){
        return pontos.stream().map(RegistroPontoMapper::toDTO).toList();
    }

    public static List<RegistroPonto> toListEntity(final List<RegistroPontoDTO> pontoDTOS){
        return pontoDTOS.stream().map(RegistroPontoMapper::toEntity).toList();
    }
}
