package br.com.zup.propostas.biometria;

import br.com.zup.propostas.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "biometrias")
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @NotBlank
    @Column(columnDefinition = "text")
    private String biometria;

    public Biometria(Cartao cartao, String biometria) {
        this.cartao = cartao;
        this.biometria = biometria;
    }

    public Long getId() {
        return id;
    }
}
