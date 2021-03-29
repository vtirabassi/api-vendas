package br.com.tirabassi.api.vendas.controllers;

import br.com.tirabassi.api.vendas.domain.entity.Cliente;
import br.com.tirabassi.api.vendas.domain.entity.Pedido;
import br.com.tirabassi.api.vendas.domain.entity.Produto;
import br.com.tirabassi.api.vendas.domain.entity.mappers.PedidoMapper;
import br.com.tirabassi.api.vendas.domain.repositories.PedidoRepository;
import br.com.tirabassi.api.vendas.domain.services.PedidoService;
import br.com.tirabassi.api.vendas.model.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Integer createPedido(@RequestBody PedidoDTO request){
        return pedidoService.createPedido(request);
    }

    @GetMapping
    public List<Pedido> get(Pedido request){

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(request, exampleMatcher);

        return pedidoRepository.findAll(example);

    }
    
}
