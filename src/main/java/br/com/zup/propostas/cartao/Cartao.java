package br.com.zup.propostas.cartao;

import br.com.zup.propostas.bloqueios.BloqueioCartao;
import br.com.zup.propostas.proposta.Proposta;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String numero;

    @NotBlank
    private String titular;

    private LocalDateTime emitidoEm;

    private BigDecimal limite;

    @OneToOne
    private Proposta proposta;

    @Enumerated(EnumType.STRING)
    private StatusCartao status = StatusCartao.DESBLOQUEADO;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private List<Biometria> biometrias = new ArrayList<>();

    @OneToMany(mappedBy = "cartao")
    private List<BloqueioCartao> bloqueios;

    @Deprecated
    public Cartao() {
    }

    public Cartao(@NotBlank String numero, @NotBlank String titular,
                  LocalDateTime emitidoEm, BigDecimal limite, Proposta proposta) {
        this.numero = numero;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.limite = limite;
        this.proposta = proposta;
    }

    public void bloquear() {
        status = StatusCartao.BLOQUEADO;
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }
}
