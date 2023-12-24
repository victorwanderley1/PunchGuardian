package br.com.wanderley.victor.punchguardian.comum.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Papel")
@Table(schema = "comum", name = "papel")
public class Papel implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_papel")
    private Integer idPapel;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;

    @Override
    public String getAuthority() {
        return getNome();
    }
}
