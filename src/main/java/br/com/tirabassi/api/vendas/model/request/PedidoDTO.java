package br.com.tirabassi.api.vendas.model.request;

import br.com.tirabassi.api.vendas.annotations.NotEmptyList;
import br.com.tirabassi.api.vendas.model.request.ItemPedidoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class PedidoDTO {

    @NotNull(message = "{pedido.idcliente.obrigatorio}")
    private Integer cliente;

    @NotNull(message = "{pedido.total.obrigatorio}")
    private BigDecimal total;

    @NotEmptyList(message = "{pedido.lista.itens.obrigatorio}")
    private List<ItemPedidoDTO> itens;

}
