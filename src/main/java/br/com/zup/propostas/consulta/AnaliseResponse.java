package br.com.zup.propostas.consulta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnaliseResponse {

	@JsonProperty
	private String documento;
	
	@JsonProperty
	private String nome;	
	
	@JsonProperty
	private StatusSolicitacao resultadoSolicitacao;
	
	@Override
	public String toString() {
		return "Resultado [documento="+documento+", nome="+nome+", resultadoSolicitacao="+resultadoSolicitacao+"]";
	}
	
	public StatusSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
}