package br.com.tirabassi.api.vendas.security;

import br.com.tirabassi.api.vendas.domain.services.impl.UsuarioServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UsuarioServiceImpl usuarioService;

    public JwtAuthFilter(JwtService jwtService, UsuarioServiceImpl usuarioService) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        if(authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            boolean valido = jwtService.tokenValido(token);

            if(valido){
                String loginUsuario = jwtService.obterLoginUsuario(token);
                UserDetails userDetails = usuarioService.loadUserByUsername(loginUsuario);

                UsernamePasswordAuthenticationToken user =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                //preciso fazer para o contexto do spring security entenda que é uma autenticacao web
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(user);

            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
