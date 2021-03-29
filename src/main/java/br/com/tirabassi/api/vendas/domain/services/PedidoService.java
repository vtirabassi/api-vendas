package br.com.tirabassi.api.vendas.domain.services;

import br.com.tirabassi.api.vendas.model.PedidoDTO;

public interface PedidoService {

    Integer createPedido(PedidoDTO dto);
}
