package br.com.zup.propostas.biometria;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.exceptionhandler.FieldErrorOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastroBiometriaController {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @PostMapping("/cartoes/{id}/biometrias")
    public ResponseEntity<?> cadastra(@PathVariable Long id,
                                      @RequestBody @Valid NovaBiometriaRequest request,
                                      UriComponentsBuilder uriBuilder) {

        Cartao cartao = em.find(Cartao.class, id);
        if(cartao == null){
            return ResponseEntity.notFound().build();
        }

        if(!request.estaEmBase64()){
            FieldErrorOutput fieldError = new FieldErrorOutput("biometria", "A biometria está inválida");
            return ResponseEntity.badRequest()
                    .body(fieldError);
        }
        Biometria biometria = new Biometria(cartao, request.getText());
        em.persist(biometria);

        URI location = uriBuilder.path("/cartoes/{idCartao}/biometrias/{idBiometria}").buildAndExpand(cartao.getId(), biometria.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
