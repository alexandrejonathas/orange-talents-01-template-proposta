package br.com.zup.propostas.databuilder;

import java.math.BigDecimal;

import br.com.zup.propostas.proposta.EnderecoRequest;
import br.com.zup.propostas.proposta.NovaPropostaRequest;

public class NovaPropostaRequestBuilder {

	private String documento;
	private String email;
	private String nome;
	private BigDecimal salario;
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;

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

	public NovaPropostaRequestBuilder comCep(String cep) {
		this.cep = cep;
		return this;
	}

	public NovaPropostaRequestBuilder comLogradouro(String logradouro) {
		this.logradouro = logradouro;
		return this;
	}

	public NovaPropostaRequestBuilder comNumero(String numero) {
		this.numero = numero;
		return this;
	}

	public NovaPropostaRequestBuilder comComplemento(String complemento) {
		this.complemento = complemento;
		return this;
	}

	public NovaPropostaRequestBuilder comBairro(String bairro) {
		this.bairro = bairro;
		return this;
	}

	public NovaPropostaRequestBuilder comCidade(String cidade) {
		this.cidade = cidade;
		return this;
	}

	public NovaPropostaRequestBuilder comUf(String uf) {
		this.uf = uf;
		return this;
	}

	public NovaPropostaRequest constroi() {
		return new NovaPropostaRequest(documento, email, nome, salario,
				new EnderecoRequest(cep, logradouro, numero, complemento, bairro, cidade, uf));
	}

}
