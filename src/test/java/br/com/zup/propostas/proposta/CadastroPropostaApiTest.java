package br.com.zup.propostas.proposta;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.propostas.databuilder.NovaPropostaRequestBuilder;

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
	public void deveCadastrarUmaProposta() throws Exception {
		
		NovaPropostaRequest request = new NovaPropostaRequestBuilder()
				.comDocumento("048.127.020-54") 
				.comEmail("j.ricardo@email.com") 
				.comNome("José Ricardo")
				.comSalario("5000")
				.comEndereco("Rua qualquer, SN")
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
				.comDocumento("048.127.020-55") 
				.comEmail("j.ricardo.email.com") 
				.comNome(null)
				.comSalario("-5000")
				.comEndereco("")
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
				.comEndereco("Rua xpto")
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
