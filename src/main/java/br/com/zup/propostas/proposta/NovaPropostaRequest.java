package br.com.zup.propostas.proposta;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.propostas.validations.CpfOrCnpj;

public class NovaPropostaRequest {

	@CpfOrCnpj
	@JsonProperty
	@NotBlank
	private String documento;
	
	@JsonProperty	
	@NotBlank
	@Email
	private String email;
	
	@JsonProperty
	@NotBlank
	private String nome;
	
	@JsonProperty
	@NotNull
	@PositiveOrZero
	private BigDecimal salario;

	@Valid
	@NotNull
	@JsonProperty
	private EnderecoRequest endereco;

	public NovaPropostaRequest(String documento, String email, String nome, 
			BigDecimal salario, EnderecoRequest endereco) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.salario = salario;
		this.endereco = endereco;
	}

	public Proposta toModel() {
		return new Proposta(documento, email, nome,
				salario, endereco.toModel());
	}

	public EnderecoRequest getEndereco() {
		return endereco;
	}

    public String getDocumento() {
		return documento;
    }
}
