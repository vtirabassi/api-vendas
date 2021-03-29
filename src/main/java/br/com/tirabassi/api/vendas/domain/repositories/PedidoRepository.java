package br.com.tirabassi.api.vendas.domain.repositories;

import br.com.tirabassi.api.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
