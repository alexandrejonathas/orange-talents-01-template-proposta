package br.com.zup.propostas.databuilder;

import java.math.BigDecimal;

import br.com.zup.propostas.proposta.NovaPropostaRequest;

public class NovaPropostaRequestBuilder {

	private String documento;
	private String email;
	private String nome;
	private BigDecimal salario;
	private String endereco;

	public NovaPropostaRequestBuilder comDocumento(String documento) {
		this.documento = documento;
		return this;
	}

	public NovaPropostaRequestBuilder comEmail(String email) {
		this.email = email;
		return this;
	}

	public NovaPropostaRequestBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public NovaPropostaRequestBuilder comSalario(String salario) {
		this.salario = new BigDecimal(salario);
		return this;
	}

	public NovaPropostaRequestBuilder comEndereco(String endereco) {
		this.endereco = endereco;
		return this;
	}

	public NovaPropostaRequest constroi() {
		return new NovaPropostaRequest(documento, email, nome, salario, endereco);
	}

}
