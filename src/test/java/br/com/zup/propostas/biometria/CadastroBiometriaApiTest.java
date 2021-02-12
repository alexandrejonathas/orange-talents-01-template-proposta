package br.com.zup.propostas.biometria;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.proposta.Endereco;
import br.com.zup.propostas.proposta.Proposta;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CadastroBiometriaApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void deveCadastrarUmaBiometria() throws Exception {

        Proposta proposta = new Proposta("001.986.670-43", "jcouves@email.com","José das Couves", new BigDecimal(5000), new Endereco("55038565", "Rua XPTO", "10", "AB", "Boa Vista", "Caruaru", "PE"));
        em.persist(proposta);

        Cartao cartao = new Cartao("999999", "José das Couves", LocalDateTime.now(), new BigDecimal("4800"), proposta);
        em.persist(cartao);

        String biometria = Base64.encodeBase64String("minhabiometria".getBytes());
        NovaBiometriaRequest request = new NovaBiometriaRequest(biometria);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/cartoes/{id}/biometrias", cartao.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
        .andDo(print())
        .andExpect(status().isCreated());
    }


}
