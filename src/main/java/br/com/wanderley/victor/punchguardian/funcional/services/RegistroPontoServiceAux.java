package br.com.wanderley.victor.punchguardian.funcional.services;

import br.com.wanderley.victor.punchguardian.comum.models.dtos.MensagemRetornoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.Profissional;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.RegistroPontoDTO;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class RegistroPontoServiceAux {
    private static RegistroPontoServiceAux registroPontoServiceAux;
    public static RegistroPontoServiceAux getInstance(){
        if(registroPontoServiceAux == null){
            registroPontoServiceAux = new RegistroPontoServiceAux();
        }
        return registroPontoServiceAux;
    }

    public MensagemRetornoDTO gerarEspelhoDePonto(List<RegistroPontoDTO> pontos){
        StringBuilder stringBuilder = new StringBuilder("Espelho de pontos: "
                .concat(pontos.get(0).getProfissional().getPessoa().getNome()));
        int i = 0;
        for(RegistroPontoDTO ponto : pontos){
            i++;
            stringBuilder.append("\n ".concat(String.valueOf(i)).concat(". "));
            stringBuilder.append(ponto.getHora().format(DateTimeFormatter.ofPattern("HH:mm:ss dd:MM:yyyy")));
            stringBuilder.append(" ");
            stringBuilder.append(ponto.getTipoPonto());
        }
        return this.gerarMensagem(stringBuilder.toString());
    }

    private MensagemRetornoDTO gerarMensagem(final String mensagem){
        return MensagemRetornoDTO.builder().mensagemRetorno(mensagem).build();
    }

    public MensagemRetornoDTO gerarMensagemRegistroPonto(final RegistroPontoDTO ponto){
        String mensagem = "Ponto de " + ponto.getTipoPonto() + " do profissional " + ponto.getProfissional().getPessoa().getNome().concat(" ") +
                "foi gerado com sucesso às ".concat(ponto.getHora().format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"))).concat(".");
        return this.gerarMensagem(mensagem);
    }

}
