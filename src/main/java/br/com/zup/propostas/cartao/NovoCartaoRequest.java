package br.com.zup.propostas.cartao;

import br.com.zup.propostas.proposta.Proposta;
import br.com.zup.propostas.validations.CpfOrCnpj;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class NovoCartaoRequest {

    @NotBlank
    @CpfOrCnpj
    @JsonProperty
    private String documento;

    @NotBlank
    @JsonProperty
    private String nome;

    @NotBlank
    @JsonProperty
    private Long idProposta;

    public NovoCartaoRequest(Proposta proposta){
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

}
