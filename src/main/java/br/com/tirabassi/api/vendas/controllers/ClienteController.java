package br.com.tirabassi.api.vendas.controllers;

import br.com.tirabassi.api.vendas.domain.entity.Cliente;
import br.com.tirabassi.api.vendas.domain.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping(value = "/clientes/{clienteId}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable("clienteId") Integer clienteId){

        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if(cliente.isPresent())
            return ResponseEntity.ok(cliente.get());

        return ResponseEntity.notFound().build();
    }


}
