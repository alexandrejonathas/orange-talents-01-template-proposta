package br.com.zup.propostas.consulta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="analise", url = "${analise.host}")
public interface ConsultaDadosSolicitanteClient {
	
	@PostMapping
	AnaliseResponse solicitaAnalise(@RequestBody AnaliseRequest request);
}
