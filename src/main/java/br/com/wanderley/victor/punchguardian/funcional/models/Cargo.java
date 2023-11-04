package br.com.wanderley.victor.punchguardian.funcional.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer id;
    private String nome;
    private String descricao;
}
