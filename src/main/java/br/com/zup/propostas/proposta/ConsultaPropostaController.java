package br.com.zup.propostas.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Optional;

@RestController
public class ConsultaPropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @GetMapping("/propostas/{id}")
    public ResponseEntity<?> busca(@PathVariable Long id) {
        Optional<Proposta> possivelProposta = propostaRepository.findById(id);
        if(possivelProposta.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PropostaResponse(possivelProposta.get()));
    }

}
