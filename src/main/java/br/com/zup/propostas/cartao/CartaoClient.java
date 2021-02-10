package br.com.zup.propostas.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "criaCartao", url = "${accounts.host}")
public interface CartaoClient {

    @PostMapping("/api/cartoes")
    NovoCartaoResponse cadastra(@RequestBody NovoCartaoRequest request);
}
