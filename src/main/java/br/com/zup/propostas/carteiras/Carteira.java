package br.com.zup.propostas.carteiras;

import br.com.zup.propostas.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="carteiras")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCarteira tipoCarteira;

    @Deprecated
    public Carteira() {
    }

    public Carteira(@NotNull Cartao cartao, @NotBlank String email, @NotNull TipoCarteira tipoCarteira) {
        this.cartao = cartao;
        this.email = email;
        this.tipoCarteira = tipoCarteira;
    }

    public Long getId() {
        return id;
    }
}
