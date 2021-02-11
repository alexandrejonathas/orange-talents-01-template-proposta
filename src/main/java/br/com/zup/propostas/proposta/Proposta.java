package br.com.zup.propostas.proposta;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.cartao.NovoCartaoResponse;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "propostas")
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank 
	private String documento;

	@NotBlank 
	@Email 
	private String email;
	
	@NotBlank 
	private String nome;
	
	@NotNull 
	@PositiveOrZero 
	private BigDecimal salario;

	@Valid
	@NotNull
	@Embedded
	private Endereco endereco;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private EstadoProposta estado = EstadoProposta.CRIADO;

	@OneToOne(mappedBy = "proposta", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Cartao cartao;

	@Deprecated
	public Proposta() {}
	
	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotNull @PositiveOrZero BigDecimal salario, @NotBlank Endereco endereco) {
				this.documento = documento;
				this.email = email;
				this.nome = nome;
				this.salario = salario;
				this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Proposta [id="+id+ "documento="+documento+", email="+email+", nome="+nome+", salario="+salario+", endereco="+endereco+"]";
	}

	public void atualizaEstado(EstadoProposta estado) {
		this.estado = estado;
	}

	public void associaCartao(NovoCartaoResponse response) {
		this.cartao = new Cartao(response.getId(), response.getTitular(), response.getEmitidoEm(), response.getLimite(), this);
	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Cartao getCartao() {
		return cartao;
	}
}
