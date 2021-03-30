package br.com.tirabassi.api.vendas.configuration;

import br.com.tirabassi.api.vendas.domain.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/v1/clientes/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/v1/pedidos/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/v1/produtos/**")
                        .hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/v1/usuarios/**")
                        .permitAll()
                    .antMatchers(HttpMethod.GET, "/v1/usuarios/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
                .and()
                .httpBasic();
    }
}
