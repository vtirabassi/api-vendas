package br.com.tirabassi.api.vendas.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class StatusPedidoDTO {

    @NotEmpty(message = "Campo status é obrigatório")
    private String novoStatus;
}
