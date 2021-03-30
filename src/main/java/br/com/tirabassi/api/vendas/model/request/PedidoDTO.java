package br.com.tirabassi.api.vendas.model.request;

import br.com.tirabassi.api.vendas.model.request.ItemPedidoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class PedidoDTO {

    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;

}
