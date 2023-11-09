package br.com.wanderley.victor.punchguardian.funcional.models.mappers;

import br.com.wanderley.victor.punchguardian.funcional.models.Profissional;
import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.RegistroPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.EspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.ProfissionalEspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.RegistroPontoEspelhoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EspelhoPontoMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static EspelhoPontoDTO toDTO(List<RegistroPonto> pontos){
        Profissional profissional = pontos.get(0).getProfissional();
        return EspelhoPontoDTO.builder()
                .profissional(
                        new ProfissionalEspelhoPontoDTO(profissional.getPessoa().getNome(),
                                CargoMapper.toDTO(profissional.getCargo())))
                .pontos(separarPontosPorData(pontos)).build();
    }

    private static Map<String, List<RegistroPontoEspelhoDTO>> separarPontosPorData(List<RegistroPonto> pontos){
        Map<String, List<RegistroPontoEspelhoDTO>> pontosDias = new HashMap<>();
        for(RegistroPonto ponto : pontos){
            String data = ponto.getHora().format(DateTimeFormatter.ISO_LOCAL_DATE);
            if(!pontosDias.containsKey(data)){
                List<RegistroPontoEspelhoDTO> pontosDia = new ArrayList<>();
                pontosDia.add(RegistroPontoEspelhoMapper.toDTO(ponto));
                pontosDias.put(data, pontosDia);
            } else {
              pontosDias.get(data).add(RegistroPontoEspelhoMapper.toDTO(ponto));
            }
        }
        return pontosDias;
    }
}
