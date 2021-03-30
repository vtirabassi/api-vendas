package br.com.tirabassi.api.vendas.security;

import br.com.tirabassi.api.vendas.domain.entity.Usuario;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.tempo.expiracao}")
    private String tempoExpiracao;

    @Value("${jwt.chave.assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario){

        Long tempoExp = Long.valueOf(this.tempoExpiracao);
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(tempoExp);
        Date date = Date.from(localDateTime.atZone(ZoneOffset.systemDefault()).toInstant());

        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();

    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException{
        Claims claims = obterClaims(token);
        return claims.getSubject();
    }

    public boolean tokenValido(String token){

        try{
            Claims claims = obterClaims(token);
            Date dataExp = claims.getExpiration();
            LocalDateTime data = dataExp.toInstant().atZone(ZoneOffset.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now()
                    .isAfter(data);

        }catch (Exception ex){
            return false;
        }
    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

}
