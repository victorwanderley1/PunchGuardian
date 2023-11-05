package br.com.wanderley.victor.punchguardian.funcional.models.mappers;

import br.com.wanderley.victor.punchguardian.funcional.models.Profissional;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.ProfissionalDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProfissionalMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static ProfissionalDTO toDTO(Profissional profissional){
        return MODEL_MAPPER.map(profissional, ProfissionalDTO.class);
    }

    public static Profissional toEntity(ProfissionalDTO profissionalDTO){
        return MODEL_MAPPER.map(profissionalDTO, Profissional.class);
    }
}
