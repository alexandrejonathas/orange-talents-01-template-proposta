package br.com.zup.propostas.proposta;

import br.com.zup.propostas.cartao.CartaoClient;
import br.com.zup.propostas.cartao.NovoCartaoResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ConsultaCartaoTask {

    private static final Logger log = LoggerFactory.getLogger(ConsultaCartaoTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private CartaoClient cartaoClient;

    @Scheduled(fixedDelayString = "${proposta.consulta-cartao.fixedDelayString}")
    public void consultaCartaoDasPropostasElegiveis() {
        List<Proposta> propostas = repository.findAllElegiveisSemCartao();
        for(Proposta proposta : propostas) {
            try {
                NovoCartaoResponse response = cartaoClient.consulta(proposta.getId());
                proposta.associaCartao(response);
                repository.save(proposta);
            }catch (FeignException ex){
                log.info("Não encontrou um cartão para a proposta: "+proposta.getId());
            }
        }
    }

}
