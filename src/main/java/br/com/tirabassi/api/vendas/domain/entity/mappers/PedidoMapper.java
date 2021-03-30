package br.com.tirabassi.api.vendas.domain.entity.mappers;

import br.com.tirabassi.api.vendas.domain.entity.Pedido;
import br.com.tirabassi.api.vendas.model.response.ItemPedidoResponseDTO;
import br.com.tirabassi.api.vendas.model.response.PedidoResponseDTO;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoResponseDTO toResponseDTO(Pedido pedido){

        List<ItemPedidoResponseDTO> itemPedidoResponseDTOS =
                pedido.getItens().isEmpty()
                        ? Collections.emptyList()
                        : pedido.getItens()
                                .stream()
                                .map(itemPedido -> ItemPedidoMapper.toResponseDTO(itemPedido))
                                .collect(Collectors.toList());


        return PedidoResponseDTO
                .builder()
                .id(pedido.getId())
                .nomeCliente(pedido.getCliente().getNome())
                .cpfCliente(pedido.getCliente().getCpf())
                .dataPedido(pedido.getDataPedido().atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime())
                .status(pedido.getStatusPedido().name())
                .valorTotal(pedido.getValorTotal())
                .itens(itemPedidoResponseDTOS)
                .build();
    }
}
