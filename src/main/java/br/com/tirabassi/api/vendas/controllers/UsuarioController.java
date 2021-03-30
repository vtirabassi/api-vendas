package br.com.tirabassi.api.vendas.controllers;

import br.com.tirabassi.api.vendas.domain.entity.Usuario;
import br.com.tirabassi.api.vendas.domain.repositories.UsuarioRepository;
import br.com.tirabassi.api.vendas.domain.services.impl.UsuarioServiceImpl;
import br.com.tirabassi.api.vendas.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private UsuarioServiceImpl usuarioService;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioServiceImpl usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody @Valid Usuario usuario){
        return usuarioService.create(usuario);
    }

    @GetMapping("{login}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario findByLogin(@PathVariable String login){
        return usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new NegocioException("teste"));
    }

}
