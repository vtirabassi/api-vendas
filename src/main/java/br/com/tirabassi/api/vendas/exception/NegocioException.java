package br.com.tirabassi.api.vendas.exception;

public class NegocioException extends RuntimeException{

    public NegocioException(String message) {
        super(message);
    }
}
