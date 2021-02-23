package br.com.zup.propostas.proposta;

import br.com.zup.propostas.databuilder.NovaPropostaRequestBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CadastroPropostaApiTest {

    /*@Autowired
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
        String token = obtainAccessToken("joao", "123456");
        mockMvc.perform(
                post("/propostas")
                        .header("Authorization", "Bearer "+token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)
                        )
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



    private String obtainAccessToken(String username, String password) throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "client-proposta");
        params.add("client_secret", "bb3a505f-0251-4167-b6b7-88cc22c140c3");
        params.add("username", username);
        params.add("password", password);

        ResultActions result
                = mockMvc.perform(post("http://localhost:18080/auth/realms/nosso-cartao/protocol/openid-connect/token")
                .params(params)
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
    */
}
