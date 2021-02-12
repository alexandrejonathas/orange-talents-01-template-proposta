package br.com.zup.propostas.cartao;

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
    private String biometria;

    public Biometria(Cartao cartao, String biometria) {
        this.cartao = cartao;
        this.biometria = biometria;
    }

    public Long getId() {
        return id;
    }
}
