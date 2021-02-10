package br.com.zup.propostas.proposta;

import br.com.zup.propostas.databuilder.NovaPropostaRequestBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CadastroPropostaApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void deveCadastrarUmaPropostaElegivel() throws Exception {

        NovaPropostaRequest request = new NovaPropostaRequestBuilder()
                .comDocumento("048.127.020-54")
                .comEmail("j.ricardo@email.com")
                .comNome("José Ricardo")
                .comSalario("5000")
                .comCep("55.130-000")
                .comLogradouro("Rua qualquer")
                .comNumero("10A")
                .comComplemento("Aqui vai um complemento")
                .comBairro("Bairro X")
                .comCidade("Cidade Y")
                .comUf("XY")
                .constroi();

        mockMvc.perform(
                post("/propostas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void deveCadastrarUmaPropostaNaoElegivel() throws Exception {

        NovaPropostaRequest request = new NovaPropostaRequestBuilder()
                .comDocumento("331.412.680-88")
                .comEmail("j.ricardo@email.com")
                .comNome("José Ricardo")
                .comSalario("5000")
                .comCep("55.130-000")
                .comLogradouro("Rua qualquer")
                .comNumero("10A")
                .comComplemento("Aqui vai um complemento")
                .comBairro("Bairro X")
                .comCidade("Cidade Y")
                .comUf("XY")
                .constroi();

        mockMvc.perform(
                post("/propostas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void naoDeveCadastrarUmaPropostaComDadosInvalidos() throws Exception {

        NovaPropostaRequest request = new NovaPropostaRequestBuilder()
                .comDocumento("048.127.020-54")
                .comEmail("j.ricardo@email.com")
                .comNome("José Ricardo")
                .comSalario("5000")
                .constroi();

        mockMvc.perform(
                post("/propostas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void naoDeveCadastrarUmaPropostaComDocumentoJaCadastrado() throws Exception {

        NovaPropostaRequest request = new NovaPropostaRequestBuilder()
                .comDocumento("048.127.020-54")
                .comEmail("j.ricardo@email.com")
                .comNome("José Ricardo")
                .comSalario("5000")
                .comCep("55.130-000")
                .comLogradouro("Rua qualquer")
                .comNumero("10A")
                .comComplemento("Aqui vai um complemento")
                .comBairro("Bairro X")
                .comCidade("Cidade Y")
                .comUf("XY")
                .constroi();

        Proposta proposta = request.toModel();
        em.persist(proposta);

        mockMvc.perform(
                post("/propostas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}
