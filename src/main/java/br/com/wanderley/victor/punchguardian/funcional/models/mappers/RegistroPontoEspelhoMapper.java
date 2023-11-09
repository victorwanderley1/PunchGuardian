package br.com.wanderley.victor.punchguardian.funcional.models.mappers;

import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.RegistroPontoEspelhoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RegistroPontoEspelhoMapper {
    private final static ModelMapper MODEL_MAPPER = new ModelMapper();

    public static RegistroPontoEspelhoDTO toDTO(final RegistroPonto ponto){
        return RegistroPontoEspelhoDTO.builder()
                .id(ponto.getId())
                .tipoPonto(ponto.getTipoPonto())
                .hora(ponto.getHora())
                .correcao(ponto.getCorrecao()).build();
    }
}
