package br.com.zup.propostas.bloqueios;

import br.com.zup.propostas.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "bloqueios")
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cartao cartao;

    @CreationTimestamp
    private LocalDateTime instante;

    @NotBlank
    private String ip;

    @NotBlank
    private String userAgent;

    @Deprecated
    public BloqueioCartao() {
    }

    public BloqueioCartao(@NotNull Cartao cartao, @NotBlank String ip, @NotBlank String userAgent) {
        this.cartao = cartao;
        this.ip = ip;
        this.userAgent = userAgent;
    }
}
