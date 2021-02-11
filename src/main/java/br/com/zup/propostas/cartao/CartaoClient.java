package br.com.zup.propostas.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartao", url = "${accounts.host}")
public interface CartaoClient {

    @GetMapping("/api/cartoes")
    NovoCartaoResponse consulta(@RequestParam Long idProposta);
}
