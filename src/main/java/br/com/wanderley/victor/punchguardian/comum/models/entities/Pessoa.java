package br.com.wanderley.victor.punchguardian.comum.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "comum", name = "pessoa")
@Entity
public class Pessoa {
    @Id
    @Column(name = "id_pessoa")
    private Integer id;
    @Column(name="nome")
    private String nome;
    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "email")
    private String email;
}
