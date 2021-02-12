package br.com.zup.propostas.proposta;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class PropostaResponse {

    @JsonProperty
    private String documento;
    @JsonProperty
    private String email;
    @JsonProperty
    private String nome;
    @JsonProperty
    private BigDecimal salario;
    @JsonProperty
    private EnderecoResponse endereco;
    @JsonProperty
    private String status;

    public PropostaResponse(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.salario = proposta.getSalario();
        this.endereco = new EnderecoResponse(proposta.getEndereco());
        this.status = proposta.getEstado().getStatus();
    }

}
