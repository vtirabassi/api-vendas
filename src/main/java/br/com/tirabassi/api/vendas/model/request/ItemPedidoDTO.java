package br.com.tirabassi.api.vendas.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDTO {

    private Integer produto;
    private Integer quantidade;

}
