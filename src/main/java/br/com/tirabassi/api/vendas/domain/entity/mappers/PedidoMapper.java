package br.com.tirabassi.api.vendas.domain.entity.mappers;

import br.com.tirabassi.api.vendas.domain.entity.Pedido;
import br.com.tirabassi.api.vendas.model.PedidoDTO;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PedidoMapper {

    private static ModelMapper modelMapper;

    @Autowired
    private ModelMapper mapper;

    @PostConstruct
    private void init(){
        modelMapper = mapper;
    }

    public static Pedido toEntity(PedidoDTO pedidoDTO){

        Pedido pedido = modelMapper.map(pedidoDTO, Pedido.class);

        return pedido;
    }
}
