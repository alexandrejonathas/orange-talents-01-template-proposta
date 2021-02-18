package br.com.zup.propostas.bloqueios;

import br.com.zup.propostas.cartao.*;
import br.com.zup.propostas.exceptionhandler.FieldErrorOutput;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class BloqueioCartaoController {

    private final String APP_NAME = "proposta-api";

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BloqueioCartaoRepository bloqueioRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PutMapping("/cartoes/{id}/bloqueio")
    public ResponseEntity<?> bloqueio(@PathVariable Long id, HttpServletRequest request) {
        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

        if(possivelCartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = possivelCartao.get();
        try{
            cartaoClient.bloqueio(cartao.getNumero(), new BloqueioCartaoRequest(APP_NAME));
        }catch (FeignException.UnprocessableEntity ex) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("violacaoRegraDeNegocio", "O cartão já está bloqueado!");
            return ResponseEntity.unprocessableEntity().body(errors);
        }

        cartao.bloquear();
        Map<String, String> headers = getRequestHeaders(request);
        BloqueioCartao bloqueio = new BloqueioCartao(cartao, headers.get("ip"), headers.get("user-agent"));
        bloqueioRepository.save(bloqueio);

        return ResponseEntity.ok().build();
    }

    private Map<String, String> getRequestHeaders(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();
        String ip = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());

        if(ip.equals("0:0:0:0:0:0:0:1"))
            ip = "127.0.0.1";
        result.put("ip", ip);

        String userAgent = request.getHeader("User-Agent");
        result.put("user-agent", userAgent);

        return result;
    }


}
