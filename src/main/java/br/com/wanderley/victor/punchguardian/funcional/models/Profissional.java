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
    private Integer id;
    @ManyToOne(cascade=ALL)
    private Cargo cargo;
    private Integer cargaHorariaSemanal;
    @OneToOne
    private Pessoa pessoa;
}
