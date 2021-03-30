package br.com.tirabassi.api.vendas.exceptionhandler;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

public class ForbiddenExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {

        JSONObject json = new JSONObject();
        json.put("dataHora", OffsetDateTime.now().toString());
        json.put("status", String.valueOf(403));
        json.put("message", "Access denied");


        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        res.getWriter().write(json.toString());

    }
}
