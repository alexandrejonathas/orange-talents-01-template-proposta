package br.com.zup.propostas.carteiras;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "carteira", url = "${accounts.host}")
public interface CarteiraClient {

    @PostMapping("/api/cartoes/{id}/carteiras")
    CarteiraClientResponse adiciona(@PathVariable String id, @RequestBody CarteiraClienteRequest request);
}
