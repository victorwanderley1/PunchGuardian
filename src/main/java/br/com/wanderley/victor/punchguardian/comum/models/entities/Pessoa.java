package br.com.wanderley.victor.punchguardian.comum.models.entities;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_id_pessoa_seq")
    @SequenceGenerator(name = "pessoa_id_pessoa_seq", sequenceName = "pessoa_id_pessoa_seq", allocationSize = 0)
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
