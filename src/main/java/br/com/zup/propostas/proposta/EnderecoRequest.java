package br.com.zup.propostas.proposta;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {

    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;
    @NotBlank
    private String complemento;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public EnderecoRequest(@NotBlank String cep, @NotBlank String logradouro, @NotBlank String numero,
                           @NotBlank String complemento, @NotBlank String bairro,
                           @NotBlank String cidade, @NotBlank String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Endereco toModel() {
        return new Endereco(cep, logradouro, numero, complemento, bairro, cidade, uf);
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }
}
