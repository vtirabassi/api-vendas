package br.com.tirabassi.api.vendas.controllers;

import br.com.tirabassi.api.vendas.domain.entity.Produto;
import br.com.tirabassi.api.vendas.domain.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("v1/produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<Produto> get(Produto produto){
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(produto, exampleMatcher);

        return produtoRepository.findAll(example);
    }

    @GetMapping("{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public Produto getById(@PathVariable Integer produtoId){

        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto createProduto(@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }

    @PutMapping("{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public Produto updateProduto(@PathVariable Integer produtoId, @RequestBody Produto produto){

        return produtoRepository.findById(produtoId)
                .map(p -> {
                    produto.setId(p.getId());
                    return produtoRepository.save(produto);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

    }

    @DeleteMapping("{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable Integer produtoId){

        produtoRepository.findById(produtoId)
                .map(p -> {
                    produtoRepository.deleteById(p.getId());
                    return Void.class;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }



}
