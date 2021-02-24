package br.com.zup.propostas.proposta;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import br.com.zup.propostas.cartao.CartaoClient;
import br.com.zup.propostas.comum.CryptDecryptUtil;
import br.com.zup.propostas.exceptionhandler.FieldErrorOutput;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.propostas.consulta.AnaliseRequest;
import br.com.zup.propostas.consulta.AnaliseResponse;
import br.com.zup.propostas.consulta.AnaliseClient;

@RestController
public class CadastroPropostaController {

	@Autowired
	private PropostaRepository repository;
	
	@Autowired
	private AnaliseClient analiseClient;

	@Autowired
	private CartaoClient cartaoClient;

	@Autowired
	private CryptDecryptUtil dataCryptUtil;

	@PostMapping("/propostas")
	public ResponseEntity<?> cadastra(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uriBuilder) throws InterruptedException {
		Optional<Proposta> possivelProposta = repository.findByDocumento(request.getDocumento());
		if(possivelProposta.isPresent()) {
			return ResponseEntity.unprocessableEntity()
					.body(new FieldErrorOutput("documento", "Já existe uma proposta cadastrada para o documento "+request.getDocumento()));
		}
		
		Proposta proposta = request.toModel();
		repository.save(proposta);

		try {
			AnaliseResponse resultadoAnalise = analiseClient.solicitaAnalise(new AnaliseRequest(proposta));
			proposta.atualizaEstado(resultadoAnalise.getResultadoSolicitacao().getEstado());
		}catch(FeignException.UnprocessableEntity ex) {
			proposta.atualizaEstado(EstadoProposta.NAO_ELEGIVEL);
		}
		repository.save(proposta);

		URI location = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}
