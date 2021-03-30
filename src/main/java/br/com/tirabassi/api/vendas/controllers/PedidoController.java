package br.com.tirabassi.api.vendas.controllers;

import br.com.tirabassi.api.vendas.domain.entity.Pedido;
import br.com.tirabassi.api.vendas.domain.enums.StatusPedido;
import br.com.tirabassi.api.vendas.domain.repositories.PedidoRepository;
import br.com.tirabassi.api.vendas.domain.services.PedidoService;
import br.com.tirabassi.api.vendas.model.request.PedidoDTO;
import br.com.tirabassi.api.vendas.model.request.StatusPedidoDTO;
import br.com.tirabassi.api.vendas.model.response.PedidoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    private PedidoRepository pedidoRepository;

    @Autowired
    public PedidoController(PedidoService pedidoService, PedidoRepository pedidoRepository) {
        this.pedidoService = pedidoService;
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createPedido(@RequestBody @Valid PedidoDTO request){
        return pedidoService.createPedido(request);
    }

    @GetMapping("{pedidoId}")
    public PedidoResponseDTO getById(@PathVariable Integer pedidoId){
        return pedidoService.getPedidoCompletoById(pedidoId);
    }

    //TODO: ajuste na rota
   @PatchMapping("{pedidoId}/atualizar")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void updateStatus(@PathVariable("pedidoId") Integer pedidoId,
                            @RequestBody @Valid StatusPedidoDTO statusPedido){

        pedidoService.atualizarStatusById(pedidoId, statusPedido);
   }

}
