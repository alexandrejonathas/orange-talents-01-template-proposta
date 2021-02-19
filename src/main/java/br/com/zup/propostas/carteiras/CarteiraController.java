package br.com.zup.propostas.carteiras;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.cartao.CartaoClient;
import br.com.zup.propostas.cartao.CartaoRepository;
import feign.FeignException;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private CarteiraClient carteiraClient;

    @PostMapping("/cartoes/{id}/carteiras")
    public ResponseEntity<?> cadastra(@PathVariable Long id,
                                      @RequestBody CarteiraRequest request,
                                      UriComponentsBuilder uriBuilder) {

        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
        if(possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = possivelCartao.get();

        Map<String, Object> errors = new HashMap<>();
        try {
            Optional<Carteira> possivelCarteira = carteiraRepository.findByTipoCarteiraAndCartao(request.getCarteira(), cartao);
            if(possivelCarteira.isPresent()){
                errors.put("violacaoRegraDeNegocio", "O cartão já está cadastrado para essa carteira!");
                return ResponseEntity.badRequest().body(errors);
            }

            carteiraClient.adiciona(cartao.getNumero(), new CarteiraClienteRequest(request));

            Carteira carteira = carteiraRepository.save(new Carteira(cartao, request.getEmail(), request.getCarteira()));
            URI location = uriBuilder.path("/cartoes/{id}/carteiras/{id}").build(cartao.getId(), carteira.getId());

            return ResponseEntity.created(location).build();
        }catch (FeignException ex){
            errors.put("violacaoRegraDeNegocio", "Não foi possível realizar a operação!");
            return ResponseEntity.unprocessableEntity().body(errors);
        }

    }

}
