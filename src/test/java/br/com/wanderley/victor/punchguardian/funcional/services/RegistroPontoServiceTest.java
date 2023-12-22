package br.com.wanderley.victor.punchguardian.funcional.services;

import br.com.wanderley.victor.punchguardian.comum.models.dtos.MensagemRetornoDTO;
import br.com.wanderley.victor.punchguardian.comum.models.entities.Pessoa;
import br.com.wanderley.victor.punchguardian.funcional.exceptions.EspelhoPontoException;
import br.com.wanderley.victor.punchguardian.funcional.models.Cargo;
import br.com.wanderley.victor.punchguardian.funcional.models.Profissional;
import br.com.wanderley.victor.punchguardian.funcional.models.RegistroPonto;
import br.com.wanderley.victor.punchguardian.funcional.models.dtos.response.espelho.EspelhoPontoDTO;
import br.com.wanderley.victor.punchguardian.funcional.models.enums.TipoPonto;
import br.com.wanderley.victor.punchguardian.funcional.repositories.ProfissionalRepository;
import br.com.wanderley.victor.punchguardian.funcional.repositories.RegistroPontoRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegistroPontoServiceTest {

    @Mock
    private ProfissionalRepository profissionalRepository;

    @Mock
    private RegistroPontoRepository pontoRepository;

    @InjectMocks
    private RegistroPontoService pontoService;

    private Pessoa pessoa = new Pessoa(1, "Testador da Silva", LocalDate.of(1990, 01, 01),
            "Rua das Laranjas", "359.726.760-28", "emaildotestador@email.com");

    private Cargo cargo = new Cargo(1,"Testador Profissional","Realizar Testes Unitários");

    private Profissional profissional = new Profissional(1, cargo, 40, pessoa);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registrarPontoComSucesso(){
        when(profissionalRepository.findById(profissional.getId())).thenReturn(Optional.of(profissional));
        when(pontoRepository.getTopByProfissionalOrderByHoraDesc(profissional))
                .thenReturn(new RegistroPonto(1L, LocalDateTime.now(), TipoPonto.ENTRADA, profissional, false));
        when(pontoRepository.save(any(RegistroPonto.class)))
                .thenReturn(new RegistroPonto(2L, LocalDateTime.now(), TipoPonto.SAIDA, profissional, false));
        MensagemRetornoDTO mensagem = pontoService.registrarPonto(profissional.getId());
        verify(profissionalRepository, times(1)).findById(profissional.getId());
        verify(pontoRepository, times(1)).getTopByProfissionalOrderByHoraDesc(profissional);
        assertNotNull(mensagem);
        assertTrue(mensagem.getMensagemRetorno().startsWith("Ponto de "+TipoPonto.SAIDA+" do profissional "+pessoa.getNome()));
    }

    @Test
    public void registrarPontoErroProfissionalNaoEncontrado(){
        when(profissionalRepository.findById(profissional.getId())).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> pontoService.registrarPonto(profissional.getId()), "Id Profissional "+profissional.getId()+" não existe");
        verify(profissionalRepository, times(1)).findById(profissional.getId());
    }

    @Test
    void testEspelhoDePontoSemIntervaloRetornoVazio() {
        when(profissionalRepository.findById(any())).thenReturn(Optional.of(profissional));
        when(pontoRepository.findByProfissionalOrderByHoraAsc(any())).thenReturn(Collections.emptyList());

        EspelhoPontoDTO espelho = pontoService.espelhoDePonto(profissional.getId());
        assertNotNull(espelho);
    }

    @Test
    void testEspelhoDePontoSemIntervaloRetornoEspelho() {
        List<RegistroPonto> pontos = List.of(new RegistroPonto(profissional, LocalDateTime.now().minusHours(1), TipoPonto.ENTRADA),
                new RegistroPonto(profissional, LocalDateTime.now(), TipoPonto.SAIDA));
        when(profissionalRepository.findById(any())).thenReturn(Optional.of(profissional));
        when(pontoRepository.findByProfissionalOrderByHoraAsc(profissional)).thenReturn(pontos);

        EspelhoPontoDTO espelho = pontoService.espelhoDePonto(profissional.getId());
        assertNotNull(espelho);
        assertEquals(Duration.ofHours(1).toHours(),espelho.getTotalHorasTrabalhadas().toHours());
    }

    @Test
    void testEspelhoPontoDataInicioMaiorDataFinal(){
        when(profissionalRepository.findById(any())).thenReturn(Optional.of(profissional));
        assertThrows(EspelhoPontoException.class,
                () -> pontoService.espelhoDePonto(profissional.getId(), LocalDate.now(), LocalDate.now().minusDays(1)),
                "A data final é anterior a data inicial");
    }


}
