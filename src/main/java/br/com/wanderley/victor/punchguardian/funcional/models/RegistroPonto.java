package br.com.wanderley.victor.punchguardian.funcional.models;

import br.com.wanderley.victor.punchguardian.funcional.models.enums.TipoPonto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "funcional", name = "registro_ponto")
public class RegistroPonto {
    @Id
    @Column(name="id_ponto")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registro_ponto_seq")
    @SequenceGenerator(name = "registro_ponto_seq", sequenceName = "registro_ponto_id_ponto_seq", schema = "funcional", allocationSize = 0)
    private Long id;
    @Column(name = "hora")
    private LocalDateTime hora;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_ponto")
    private TipoPonto tipoPonto;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_profissional")
    private Profissional profissional;
    @Column(name = "correcao")
    private Boolean correcao;

    public RegistroPonto(final Profissional profissional, final LocalDateTime hora, final TipoPonto tipoPonto){
        this.profissional = profissional;
        this.hora = hora;
        this.tipoPonto = tipoPonto;
    }
}
