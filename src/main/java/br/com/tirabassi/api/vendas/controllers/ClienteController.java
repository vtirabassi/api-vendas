package br.com.tirabassi.api.vendas.controllers;

import br.com.tirabassi.api.vendas.domain.entity.Cliente;
import br.com.tirabassi.api.vendas.domain.repositories.ClienteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/clientes")
@Api("API Clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @ApiOperation("Obter Clientes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Clientes encontrados"),
            @ApiResponse(code = 404, message = "Clientes não encontrados")
    })
    @GetMapping()
    public List<Cliente> get(Cliente request){

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(request, exampleMatcher);

        return clienteRepository.findAll(example);

    }

    @ApiOperation("Obter dados de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")
    })
    @GetMapping(value = "{clienteId}")
    public Cliente getClienteById(@PathVariable("clienteId") Integer clienteId){

        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    //TODO: REGEX CPF para somente letras
    @ApiOperation("Criar um cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente criado"),
            @ApiResponse(code = 400, message = "Erro de validações")
    })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente createCliente(@RequestBody @Valid Cliente request){
        return clienteRepository.save(request);
    }

    @ApiOperation("Deletar um cliente")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cliente deletado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")
    })
    @DeleteMapping(value = "{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer clienteId) throws ResponseStatusException{

        clienteRepository.findById(clienteId)
        .map(cliente -> {
            clienteRepository.delete(cliente);
            return cliente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    @ApiOperation("Atualizar um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente atualizado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")
    })
    @PutMapping("{clienteId}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente updateCliente(@PathVariable Integer clienteId, @RequestBody @Valid Cliente request) {

        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        return cliente.map(c -> {
            request.setId(c.getId());
            return clienteRepository.save(request);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }



}
