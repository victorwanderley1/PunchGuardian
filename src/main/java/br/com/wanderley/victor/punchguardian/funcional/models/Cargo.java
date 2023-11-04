package br.com.wanderley.victor.punchguardian.funcional.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "funcional", name = "cargo")
public class Cargo {
    @Id
    @Column(name = "id_cargo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_id_cargo_seq")
    @SequenceGenerator(name = "cargo_id_cargo_seq", sequenceName = "cargo_id_cargo_seq")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
}
