package br.com.wanderley.victor.punchguardian.funcional.models.mappers;

import br.com.wanderley.victor.punchguardian.funcional.models.Cargo;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.CargoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CargoMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static CargoDTO toDTO(Cargo cargo){
        return MODEL_MAPPER.map(cargo, CargoDTO.class);
    }

    public static Cargo toEntity(CargoDTO cargoDTO){
        return MODEL_MAPPER.map(cargoDTO, Cargo.class);
    }
}
