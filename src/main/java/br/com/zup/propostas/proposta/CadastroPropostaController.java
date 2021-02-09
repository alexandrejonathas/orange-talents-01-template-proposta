package br.com.zup.propostas.proposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CadastroPropostaController {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@PostMapping("/propostas")
	public ResponseEntity<?> cadastra(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uriBuilder) {
		
		if(request.existeUmaPropostaParaODocumento(em)) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		Proposta proposta = request.toModel();
		em.persist(proposta);
		
		URI location = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}
