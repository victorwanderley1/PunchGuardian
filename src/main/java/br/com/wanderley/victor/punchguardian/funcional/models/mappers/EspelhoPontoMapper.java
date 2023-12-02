package br.com.wanderley.victor.punchguardian.funcional.models.mappers;

import br.com.wanderley.victor.punchguardian.funcional.models.Profissional;
import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.DiaRegistradoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.EspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.ProfissionalEspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.RegistroPontoEspelhoDTO;
import br.com.wanderley.victor.punchguardian.funcional.services.RegistroPontoServiceAux;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EspelhoPontoMapper {

    public static EspelhoPontoDTO toDTO(List<RegistroPonto> pontos){
        Profissional profissional = pontos.get(0).getProfissional();
        EspelhoPontoDTO espelho = EspelhoPontoDTO.builder()
                .profissional(
                        new ProfissionalEspelhoPontoDTO(profissional.getPessoa().getNome(),
                                CargoMapper.toDTO(profissional.getCargo())))
                .dias(separarPontosPorData(pontos)).build();
    }

    private static List<DiaRegistradoDTO> separarPontosPorData(List<RegistroPonto> pontos){
        List<DiaRegistradoDTO> dias = new ArrayList<>();
        Map<String, Integer> diasJaRegistrados = new HashMap<>();

        for(RegistroPonto ponto : pontos){
            String data = ponto.getHora().format(DateTimeFormatter.ISO_LOCAL_DATE);

            if(!diasJaRegistrados.containsKey(data)){
                List<RegistroPontoEspelhoDTO> pontosDia = new ArrayList<>();
                pontosDia.add(RegistroPontoEspelhoMapper.toDTO(ponto));
                dias.add(new DiaRegistradoDTO(ponto.getHora().toLocalDate(), pontosDia, LocalTime.MIN));
                diasJaRegistrados.put(data,diasJaRegistrados.size());
            } else {
                DiaRegistradoDTO dia = dias.get(diasJaRegistrados.get(data));
                dia.getPontos().add(RegistroPontoEspelhoMapper.toDTO(ponto));
            }
            for(DiaRegistradoDTO dia: dias){
                dia.setTotalHorasRegistradas(somaHorasDia(dia));
            }


        }
        return dias;
    }

    private static LocalTime somaHorasDia(DiaRegistradoDTO dia){
        List<RegistroPontoEspelhoDTO> pontos = dia.getPontos();
        if(pontos.size()>1) {
            Duration primeiraJornada = Duration.between(pontos.get(0).getHora(), pontos.get(1).getHora());
            if(pontos.size()==4){
                Duration segundaJornada = Duration.between(pontos.get(2).getHora(), pontos.get(3).getHora());
                return LocalTime.MIN.plus(primeiraJornada.plus(segundaJornada));
            }
            return LocalTime.MIN.plus(primeiraJornada);
        }
        return LocalTime.MIN;
                .dias(RegistroPontoServiceAux.getInstance().separarPontosPorData(pontos)).build();
        return espelho;
    }
}
