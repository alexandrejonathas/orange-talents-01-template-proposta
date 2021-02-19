package br.com.zup.propostas.cartao;

import br.com.zup.propostas.avisos.AvisoClientRequest;
import br.com.zup.propostas.avisos.AvisoClientResponse;
import br.com.zup.propostas.avisos.NovoAvisoRequest;
import br.com.zup.propostas.bloqueios.BloqueioCartaoRequest;
import br.com.zup.propostas.bloqueios.BloqueioCartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartao", url = "${accounts.host}")
public interface CartaoClient {

    @GetMapping("/api/cartoes")
    NovoCartaoResponse consulta(@RequestParam Long idProposta);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    BloqueioCartaoResponse bloqueio(@PathVariable String id, @RequestBody BloqueioCartaoRequest request);

    @PostMapping("/api/cartoes/{id}/avisos")
    AvisoClientResponse avisa(@PathVariable String id, @RequestBody AvisoClientRequest request);

}
