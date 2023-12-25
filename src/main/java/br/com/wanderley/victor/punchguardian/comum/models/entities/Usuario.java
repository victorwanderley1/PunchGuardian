package br.com.wanderley.victor.punchguardian.comum.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Usuario")
@Table(schema = "comum", name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    @Column(name = "dt_cadastro")
    private LocalDate dataCadastro;
    private Boolean ativo;
    @Column(name = "id_pessoa")
    private Integer idPessoa;
    @Column(name = "username")
    private String username;
    @Column(name = "senha")
    private String senha;
    @ManyToMany
    @JoinTable(schema = "comum", name = "usuario_papeis", joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_papel"))
    private List<Papel> papeis;

    public Usuario(Integer idPessoa, String username, String senha, List<Papel> papeis, LocalDate dataCadastro) {
        this.idPessoa = idPessoa;
        this.username = username;
        this.senha = senha;
        this.papeis = papeis;
        this.dataCadastro = dataCadastro;
        this.ativo = true;
    }
}
