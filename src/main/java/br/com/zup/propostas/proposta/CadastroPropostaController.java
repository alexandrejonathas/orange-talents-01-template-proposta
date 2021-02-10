package br.com.zup.propostas.proposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import br.com.zup.propostas.cartao.CartaoClient;
import br.com.zup.propostas.cartao.NovoCartaoRequest;
import br.com.zup.propostas.cartao.NovoCartaoResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.propostas.consulta.AnaliseRequest;
import br.com.zup.propostas.consulta.AnaliseResponse;
import br.com.zup.propostas.consulta.AnaliseClient;

@RestController
public class CadastroPropostaController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private AnaliseClient consultaDadosSolicitanteClient;

	@Autowired
	private CartaoClient cartaoClient;

	@Transactional
	@PostMapping("/propostas")
	public ResponseEntity<?> cadastra(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uriBuilder) {
		
		if(request.existeUmaPropostaParaODocumento(em)) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		Proposta proposta = request.toModel();
		em.persist(proposta);

		try {
			AnaliseResponse resultadoAnalise = consultaDadosSolicitanteClient.solicitaAnalise(new AnaliseRequest(proposta));
			proposta.atualizaEstado(resultadoAnalise.getResultadoSolicitacao().getEstado());
		}catch(FeignException.UnprocessableEntity ex) {
			proposta.atualizaEstado(EstadoProposta.NAO_ELEGIVEL);
		}
		em.persist(proposta);

		//TODO: Aqui deveria retornar os dados do cartão criado, segundo a documentação da API
		NovoCartaoResponse response = cartaoClient.cadastra(new NovoCartaoRequest(proposta));

		System.out.println(">>>"+response);

		URI location = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}
