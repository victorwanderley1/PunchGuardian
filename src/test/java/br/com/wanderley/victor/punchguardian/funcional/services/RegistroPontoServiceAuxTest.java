package br.com.wanderley.victor.punchguardian.funcional.services;

import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.DiaRegistradoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.EspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.RegistroPontoEspelhoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.enums.TipoPonto;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegistroPontoServiceAuxTest {

    private static EspelhoPontoDTO espelho;

    @BeforeAll
    static void setUp(){
        List<RegistroPontoEspelhoDTO> pontos = new ArrayList<>();
        pontos.add(RegistroPontoEspelhoDTO.builder().tipoPonto(TipoPonto.ENTRADA).hora(LocalDateTime.of(2023, Month.JANUARY, 14, 8, 3, 0)).build());
        pontos.add(RegistroPontoEspelhoDTO.builder().tipoPonto(TipoPonto.SAIDA).hora(LocalDateTime.of(2023, Month.JANUARY, 14, 12, 18, 0)).build());
        pontos.add(RegistroPontoEspelhoDTO.builder().tipoPonto(TipoPonto.ENTRADA).hora(LocalDateTime.of(2023, Month.JANUARY, 14, 14, 0, 0)).build());
        pontos.add(RegistroPontoEspelhoDTO.builder().tipoPonto(TipoPonto.SAIDA).hora(LocalDateTime.of(2023, Month.JANUARY, 14, 18, 13, 0)).build());
        DiaRegistradoDTO dia = DiaRegistradoDTO.builder().pontos(pontos).totalHorasRegistradas(LocalTime.of(8,28)).build();
        espelho = EspelhoPontoDTO.builder().dias(List.of(dia,dia,dia)).build();
    }


    @Test
    void somarHorasEspelhoDePonto() {
        Duration duracaoExpected = Duration.ofHours(25).plusMinutes(24);
        Duration duracaoActual = RegistroPontoServiceAux.getInstance().somarHorasEspelho(espelho);
        assertEquals(duracaoExpected, duracaoActual);
    }

    @Test
    void somarHorasDiaTrabalhado(){
        Duration duracaoExpected = Duration.ofHours(8).plusMinutes(28);
        Duration duracaoActual = RegistroPontoServiceAux.getInstance().somarHorasDia(espelho.getDias().get(0));
        assertNotNull(duracaoActual);
        assertEquals(duracaoExpected, duracaoActual);
    }

    @Test
    void somarHorasDiaTrabalhadoPontosImpares(){
        Duration duracaoExpected = Duration.ofHours(4).plusMinutes(15);
        espelho.getDias().get(0).getPontos().remove(3);
        Duration duracaoActual = RegistroPontoServiceAux.getInstance().somarHorasDia(espelho.getDias().get(0));
        assertNotNull(duracaoActual);
        assertEquals(duracaoExpected, duracaoActual);
    }

    @Test
    void somarHorasEspelhoDePontoSemHorasTotaisDia(){
        EspelhoPontoDTO espelhoPontoDTO = EspelhoPontoDTO.builder()
                .dias(List.of(DiaRegistradoDTO.builder().build()))
                .build();
        Duration duracaoExpected = Duration.ZERO;


        assertEquals(duracaoExpected, RegistroPontoServiceAux.getInstance().somarHorasEspelho(espelhoPontoDTO));
    }

    @Test
    void separarPontosPorDia(){
        List<RegistroPonto> pontos = new ArrayList<>();
        pontos.add(new RegistroPonto(null, LocalDateTime.of(2023, Month.JANUARY, 14, 8, 3, 0), TipoPonto.ENTRADA));
        pontos.add(new RegistroPonto(null, LocalDateTime.of(2023, Month.JANUARY, 14, 12, 18, 0), TipoPonto.SAIDA));
        pontos.add(new RegistroPonto(null, LocalDateTime.of(2023, Month.JANUARY, 14, 14, 30, 0), TipoPonto.ENTRADA));
        pontos.add(new RegistroPonto(null, LocalDateTime.of(2023, Month.JANUARY, 14, 18, 14, 0), TipoPonto.SAIDA));

        pontos.add(new RegistroPonto(null, LocalDateTime.of(2023, Month.JANUARY, 15, 8, 3, 0), TipoPonto.ENTRADA));
        pontos.add(new RegistroPonto(null, LocalDateTime.of(2023, Month.JANUARY, 15, 12, 18, 0), TipoPonto.SAIDA));
        pontos.add(new RegistroPonto(null, LocalDateTime.of(2023, Month.JANUARY, 15, 14, 30, 0), TipoPonto.ENTRADA));
        pontos.add(new RegistroPonto(null, LocalDateTime.of(2023, Month.JANUARY, 15, 18, 14, 0), TipoPonto.SAIDA));

        pontos.add(new RegistroPonto(null, LocalDateTime.of(2023, Month.JANUARY, 16, 8, 3, 0), TipoPonto.ENTRADA));
        pontos.add(new RegistroPonto(null, LocalDateTime.of(2023, Month.JANUARY, 16, 12, 18, 0), TipoPonto.SAIDA));
        pontos.add(new RegistroPonto(null, LocalDateTime.of(2023, Month.JANUARY, 16, 14, 30, 0), TipoPonto.ENTRADA));
        pontos.add(new RegistroPonto(null, LocalDateTime.of(2023, Month.JANUARY, 16, 18, 14, 0), TipoPonto.SAIDA));

        List<DiaRegistradoDTO> diaRegistradoDTOS = RegistroPontoServiceAux.getInstance().separarPontosPorData(pontos);
        assertEquals(3,  diaRegistradoDTOS.size());
        assertEquals(4, diaRegistradoDTOS.get(0).getPontos().size());
        assertEquals(4, diaRegistradoDTOS.get(1).getPontos().size());
        assertEquals(4, diaRegistradoDTOS.get(2).getPontos().size());
    }


}