package br.com.zup.propostas.proposta;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class EnderecoResponse {

    @JsonProperty
    private String cep;
    @JsonProperty
    private String logradouro;
    @JsonProperty
    private String numero;
    @JsonProperty
    private String complemento;
    @JsonProperty
    private String bairro;
    @JsonProperty
    private String cidade;
    @JsonProperty
    private String uf;

    public EnderecoResponse(Endereco endereco) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getUf();
    }
}
