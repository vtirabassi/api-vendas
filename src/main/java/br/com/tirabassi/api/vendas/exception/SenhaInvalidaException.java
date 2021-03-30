package br.com.tirabassi.api.vendas.exception;

public class SenhaInvalidaException extends RuntimeException {

    public SenhaInvalidaException(String message){
        super(message);
    }
}
