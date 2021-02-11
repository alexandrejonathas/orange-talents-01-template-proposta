package br.com.zup.propostas.cartao;

import br.com.zup.propostas.proposta.Proposta;
import br.com.zup.propostas.proposta.PropostaRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class NovoCartaoResponse {

    @JsonProperty
    private String id;

    @JsonProperty
    private String titular;

    @JsonProperty
    private LocalDateTime emitidoEm;

    @JsonProperty
    private Long idProposta;

    @JsonProperty
    private BigDecimal limite;

    @Override
    public String toString() {
        return "NovoCartaoResponse{" +
                "id=" + id +
                ", titular='" + titular + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", idProposta=" + idProposta +
                ", limite=" + limite +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public BigDecimal getLimite() {
        return limite;
    }
}
