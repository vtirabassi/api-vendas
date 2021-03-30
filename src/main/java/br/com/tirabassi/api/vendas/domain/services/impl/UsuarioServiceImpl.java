package br.com.tirabassi.api.vendas.domain.services.impl;

import br.com.tirabassi.api.vendas.domain.entity.Usuario;
import br.com.tirabassi.api.vendas.domain.repositories.UsuarioRepository;
import br.com.tirabassi.api.vendas.exception.SenhaInvalidaException;
import br.com.tirabassi.api.vendas.model.request.CredenciaisDTO;
import br.com.tirabassi.api.vendas.model.response.TokenDTO;
import br.com.tirabassi.api.vendas.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByLogin(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));

        String[] roles = usuario.getAdmin().equals(true)
                ? new String[]{"USER", "ADMIN"}
                : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    @Transactional
    public Usuario create(Usuario usuario) {

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public TokenDTO autenticar(CredenciaisDTO credencias) {
        try {

            UserDetails userDetails = loadUserByUsername(credencias.getLogin());
            boolean matches = passwordEncoder.matches(credencias.getSenha(), userDetails.getPassword());

            Usuario usuario = new Usuario();
            usuario.setLogin(credencias.getLogin());
            usuario.setSenha(credencias.getSenha());

            if(matches){
                String token = jwtService.gerarToken(usuario);

                return TokenDTO.builder()
                        .login(credencias.getLogin())
                        .token(token).build();
            }

            throw new SenhaInvalidaException("Senha inválida");

        }catch (UsernameNotFoundException | SenhaInvalidaException ex){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }
}
