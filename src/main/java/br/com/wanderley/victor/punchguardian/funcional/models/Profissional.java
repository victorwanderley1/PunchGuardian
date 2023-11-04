package br.com.wanderley.victor.punchguardian.funcional.models;

import br.com.wanderley.victor.punchguardian.comum.models.entities.Pessoa;
import br.com.wanderley.victor.punchguardian.funcional.models.Cargo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.ALL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "funcional", name = "profissional")
public class Profissional {

    @Id
    @Column(name = "id_profissional")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profissional_id_profissional_seq")
    @SequenceGenerator(name = "profissional_id_profissional_seq", sequenceName = "profissional_id_profissional_seq")
    private Integer id;
    @ManyToOne(cascade=ALL)
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;
    @Column(name = "carga_horaria_semanal")
    private Integer cargaHorariaSemanal;
    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
}
