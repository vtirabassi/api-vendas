package br.com.tirabassi.api.vendas.domain.entity.mappers;

import br.com.tirabassi.api.vendas.domain.entity.ItemPedido;
import br.com.tirabassi.api.vendas.domain.entity.Pedido;
import br.com.tirabassi.api.vendas.domain.entity.Produto;
import br.com.tirabassi.api.vendas.model.request.ItemPedidoDTO;
import br.com.tirabassi.api.vendas.model.response.ItemPedidoResponseDTO;
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

    public static ItemPedidoResponseDTO toResponseDTO(ItemPedido itemPedido) {

        return ItemPedidoResponseDTO
                .builder()
                .descricaoProduto(itemPedido.getProduto().getDescricao())
                .quantindade(itemPedido.getQuantidade())
                .precoUnitario(itemPedido.getProduto().getPreco())
                .build();

    }
}
