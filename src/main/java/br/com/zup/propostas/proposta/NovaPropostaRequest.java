package br.com.zup.propostas.proposta;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
	
	@JsonProperty
	@NotBlank
	private String endereco;

	public NovaPropostaRequest(String documento, String email, String nome, 
			BigDecimal salario, String endereco) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.salario = salario;
		this.endereco = endereco;
	}

	public Proposta toModel() {
		return new Proposta(documento, email, nome, salario, endereco);		
	}

	public boolean existeUmaPropostaParaODocumento(EntityManager em) {
		Query query = em.createQuery("select 1 from Proposta where documento = :documento");
		query.setParameter("documento", documento);
		var lista = query.getResultList();
		
		Assert.state(lista.size() <= 1, "Já existe uma proposta cadastrada para o documento: "+documento);
		
		return !lista.isEmpty();
	}
	
}
