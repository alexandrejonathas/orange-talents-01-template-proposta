package br.com.zup.propostas.carteiras;

import br.com.zup.propostas.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Optional<Carteira> findByTipoCarteiraAndCartao(TipoCarteira tipoCarteira, Cartao cartao);

}
