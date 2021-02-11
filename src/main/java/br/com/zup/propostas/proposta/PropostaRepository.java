package br.com.zup.propostas.proposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	Optional<Proposta> findByDocumento(String documento);

	@Query("select p from Proposta p LEFT JOIN p.cartao c where p.estado = 'ELEGIVEL' and c is null")
	List<Proposta> findAllElegiveisSemCartao();
}
