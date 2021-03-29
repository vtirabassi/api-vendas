package br.com.tirabassi.api.vendas.domain.services.impl;

import br.com.tirabassi.api.vendas.domain.entity.Cliente;
import br.com.tirabassi.api.vendas.domain.entity.ItemPedido;
import br.com.tirabassi.api.vendas.domain.entity.Pedido;
import br.com.tirabassi.api.vendas.domain.entity.Produto;
import br.com.tirabassi.api.vendas.domain.entity.mappers.ItemPedidoMapper;
import br.com.tirabassi.api.vendas.domain.repositories.ClienteRepository;
import br.com.tirabassi.api.vendas.domain.repositories.ItemPedidoRepository;
import br.com.tirabassi.api.vendas.domain.repositories.PedidoRepository;
import br.com.tirabassi.api.vendas.domain.repositories.ProdutoRepository;
import br.com.tirabassi.api.vendas.domain.services.PedidoService;
import br.com.tirabassi.api.vendas.exception.NegocioException;
import br.com.tirabassi.api.vendas.model.PedidoDTO;
import com.fasterxml.jackson.databind.node.POJONode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Override
    @Transactional
    public Integer createPedido(PedidoDTO dto) {

        if(dto.getItens().isEmpty())
            throw new NegocioException("Não é possivel fazer um pedido sem itens");

        Cliente cliente = clienteRepository
                .findById(dto.getCliente())
                .orElseThrow(() -> new NegocioException("ClienteId inválido"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDate.now());
        pedido.setValorTotal(dto.getTotal());

        List<ItemPedido> itensPedidos = dto.getItens()
                .stream()
                .map(itemPedidoDTO -> ItemPedidoMapper.toEntity(itemPedidoDTO, pedido, produtoRepository.findById(itemPedidoDTO.getProduto())
                        .orElseThrow(() -> new NegocioException("Produto não encontrado: " + itemPedidoDTO.getProduto()))))
                .collect(Collectors.toList());

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        pedido.setItens(itensPedidos);
        itemPedidoRepository.saveAll(itensPedidos);

        return pedido.getId();

    }
}
