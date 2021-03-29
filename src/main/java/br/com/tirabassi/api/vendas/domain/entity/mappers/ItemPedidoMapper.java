package br.com.tirabassi.api.vendas.domain.entity.mappers;

import br.com.tirabassi.api.vendas.domain.entity.ItemPedido;
import br.com.tirabassi.api.vendas.domain.entity.Pedido;
import br.com.tirabassi.api.vendas.domain.entity.Produto;
import br.com.tirabassi.api.vendas.model.ItemPedidoDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {


    public static ItemPedido toEntity(ItemPedidoDTO itemPedidoDTO, Pedido pedido, Produto produto){
        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());

        return itemPedido;
    }
}
