package br.com.zup.propostas.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class NovoCartaoResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String titular;

    @JsonProperty
    private LocalDateTime emitidoEm;

    @JsonProperty
    private Long idProposta;

    @Override
    public String toString() {
        return "NovoCartaoResponse{" +
                "id=" + id +
                ", emitidoEm=" + emitidoEm +
                ", idProposta=" + idProposta +
                '}';
    }
}
