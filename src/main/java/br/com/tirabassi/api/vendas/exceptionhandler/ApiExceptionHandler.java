package br.com.tirabassi.api.vendas.exceptionhandler;

import br.com.tirabassi.api.vendas.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handlerNegocioException(NegocioException ex, WebRequest webRequest){

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Error error = new Error();
        error.setDataHora(OffsetDateTime.now());
        error.setStatus(httpStatus.value());
        error.setMensagem(ex.getMessage());

        return error;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
}
