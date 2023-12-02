package br.com.wanderley.victor.punchguardian.funcional.services;

import br.com.wanderley.victor.punchguardian.comum.models.dtos.MensagemRetornoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.RegistroPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.DiaRegistradoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.EspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.RegistroPontoEspelhoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.mappers.RegistroPontoEspelhoMapper;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class RegistroPontoServiceAux {
    private static RegistroPontoServiceAux registroPontoServiceAux;
    public static RegistroPontoServiceAux getInstance(){
        if(registroPontoServiceAux == null){
            registroPontoServiceAux = new RegistroPontoServiceAux();
        }
        return registroPontoServiceAux;
    }

    private MensagemRetornoDTO gerarMensagem(final String mensagem){
        return MensagemRetornoDTO.builder().mensagemRetorno(mensagem).build();
    }

    public MensagemRetornoDTO gerarMensagemRegistroPonto(final RegistroPontoDTO ponto){
        String mensagem = "Ponto de " + ponto.getTipoPonto() + " do profissional " + ponto.getProfissional().getPessoa().getNome().concat(" ") +
                "foi gerado com sucesso às ".concat(ponto.getHora().format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy", Locale.US))).concat(".");
        return this.gerarMensagem(mensagem);
    }

    public Duration somarHorasEspelho(final EspelhoPontoDTO espelhoDTO){
        return espelhoDTO.getDias().stream()
                .filter(diaRegistradoDTO -> diaRegistradoDTO.getTotalHorasRegistradas() != null)
                .map(diaRegistradoDTO -> Duration.ofNanos(diaRegistradoDTO.getTotalHorasRegistradas().toNanoOfDay()))
                .reduce(Duration.ZERO, Duration::plus);
    }

    /**
     * Método utilizado para somar as horas de duração entre os registros de ponto do dia
     * São considerados somente pontos pares, ou seja, que houveram entrada e saída.
     * @param diaRegistradoDTO DTO do dia que terá as horas somadas
     * @return Duração entre registros dos pontos.
     */
    public Duration somarHorasDia(DiaRegistradoDTO diaRegistradoDTO){
        Iterator<RegistroPontoEspelhoDTO> pontos = diaRegistradoDTO.getPontos().iterator();
        Duration totalHoras = Duration.ZERO;
        while (pontos.hasNext()){
            RegistroPontoEspelhoDTO pontoEntrada = pontos.next();
            if (pontos.hasNext()) {
                RegistroPontoEspelhoDTO pontoSaida = pontos.next();
                totalHoras = totalHoras.plus(Duration.between(pontoEntrada.getHora(), pontoSaida.getHora()));
            }
        }
        return totalHoras;
    }

    public List<DiaRegistradoDTO> separarPontosPorData(List<RegistroPonto> pontos){
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
                dia.setTotalHorasRegistradas(LocalTime.MIN.plus(
                        RegistroPontoServiceAux.getInstance().somarHorasDia(dia)));
            }
        }
        return dias;
    }

}
