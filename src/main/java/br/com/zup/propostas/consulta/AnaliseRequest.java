package br.com.zup.propostas.consulta;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.propostas.proposta.Proposta;

public class AnaliseRequest {

	@JsonProperty
	private Long idProposta;
	
	@JsonProperty
	private String documento;
	
	@JsonProperty
	private String nome;

	public AnaliseRequest(Proposta proposta) {
		this.idProposta = proposta.getId();
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
	}
}
