package br.com.zup.propostas.consulta;

import br.com.zup.propostas.proposta.EstadoProposta;

public enum StatusSolicitacao {
	
	COM_RESTRICAO(EstadoProposta.NAO_ELEGIVEL), SEM_RESTRICAO(EstadoProposta.ELEGIVEL);
	
	private EstadoProposta estado;

	private StatusSolicitacao(EstadoProposta estado) {
		this.estado = estado;
	}

	public EstadoProposta getEstado() {
		return estado;
	}
}
