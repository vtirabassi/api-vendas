package br.com.tirabassi.api.vendas.configuration;

import br.com.tirabassi.api.vendas.domain.services.impl.UsuarioServiceImpl;
import br.com.tirabassi.api.vendas.exceptionhandler.ForbiddenExceptionHandler;
import br.com.tirabassi.api.vendas.security.JwtAuthFilter;
import br.com.tirabassi.api.vendas.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl userService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jtwFilter(){
        return new JwtAuthFilter(jwtService, userService);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new ForbiddenExceptionHandler();
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
                        .hasRole("ADMIN")
                    .anyRequest()
                        .authenticated()
                .and()
                    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(jtwFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    //Ignorar para nao passar no filter do security
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }


}
