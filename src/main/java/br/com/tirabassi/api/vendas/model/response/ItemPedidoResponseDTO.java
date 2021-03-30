package br.com.tirabassi.api.vendas.model.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedidoResponseDTO {

    private String descricaoProduto;
    private BigDecimal precoUnitario;
    private Integer quantindade;
}
