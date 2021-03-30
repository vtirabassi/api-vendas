package br.com.tirabassi.api.vendas.domain.services;

import br.com.tirabassi.api.vendas.model.request.PedidoDTO;
import br.com.tirabassi.api.vendas.model.request.StatusPedidoDTO;
import br.com.tirabassi.api.vendas.model.response.PedidoResponseDTO;

public interface PedidoService {

    Integer createPedido(PedidoDTO dto);

    PedidoResponseDTO getPedidoCompletoById(Integer pedidoId);

    void atualizarStatusById(Integer pedidoId, StatusPedidoDTO statusPedido1);
}
