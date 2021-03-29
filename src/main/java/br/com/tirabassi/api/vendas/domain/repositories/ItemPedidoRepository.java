package br.com.tirabassi.api.vendas.domain.repositories;

import br.com.tirabassi.api.vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
