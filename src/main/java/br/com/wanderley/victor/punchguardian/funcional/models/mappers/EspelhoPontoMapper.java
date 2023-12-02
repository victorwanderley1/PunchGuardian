package br.com.wanderley.victor.punchguardian.funcional.models.mappers;

import br.com.wanderley.victor.punchguardian.funcional.models.Profissional;
import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.EspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.ProfissionalEspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.services.RegistroPontoServiceAux;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EspelhoPontoMapper {

    public static EspelhoPontoDTO toDTO(List<RegistroPonto> pontos){
        Profissional profissional = pontos.get(0).getProfissional();
        EspelhoPontoDTO espelho = EspelhoPontoDTO.builder()
                .profissional(
                        new ProfissionalEspelhoPontoDTO(profissional.getPessoa().getNome(),
                                CargoMapper.toDTO(profissional.getCargo())))
                .dias(RegistroPontoServiceAux.getInstance().separarPontosPorData(pontos)).build();
        espelho.setTotalHorasTrabalhadas(RegistroPontoServiceAux.getInstance().somarHorasEspelho(espelho));
        return espelho;
    }
}
