package br.com.zup.propostas.consulta;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.propostas.proposta.Proposta;

public class AnaliseRequest {

	@JsonProperty
	private String propostaId;
	
	@JsonProperty
	private String documento;
	
	@JsonProperty
	private String nome;

	public AnaliseRequest(Proposta proposta) {
		this.propostaId = String.valueOf(proposta.getId());
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
	}
	
}
