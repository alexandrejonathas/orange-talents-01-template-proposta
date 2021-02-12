package br.com.zup.propostas.proposta;

public enum EstadoProposta {

	CRIADO, ELEGIVEL, NAO_ELEGIVEL;

	private String status;

	public String getStatus() {
		switch (this) {
			case ELEGIVEL:
				status = "Aprovada";
				break;
			case NAO_ELEGIVEL:
				status = "Não aprovada";
				break;
			default:
				status = "Em análise";
		}
		return status;
	}


}
