package br.com.tirabassi.api.vendas.model.response;


import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponseDTO {

    private Integer id;
    private String nomeCliente;
    private String cpfCliente;
    private OffsetDateTime dataPedido;
    private String status;
    private BigDecimal valorTotal;
    private List<ItemPedidoResponseDTO> itens;
}
