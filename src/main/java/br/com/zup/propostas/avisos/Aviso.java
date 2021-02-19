package br.com.zup.propostas.avisos;

import br.com.zup.propostas.cartao.Cartao;
import com.sun.xml.bind.v2.model.core.ID;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "avisos")
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cartao cartao;

    private String destino;

    private LocalDate validoAte;

    private String ip;

    private String userAgent;

    @CreationTimestamp
    private LocalDateTime instante;

    @Deprecated
    public Aviso() {
    }

    public Aviso(Cartao cartao, String destino, LocalDate validoAte, String ip, String userAgent) {
        this.cartao = cartao;
        this.destino = destino;
        this.validoAte = validoAte;
        this.ip = ip;
        this.userAgent = userAgent;
    }
}
