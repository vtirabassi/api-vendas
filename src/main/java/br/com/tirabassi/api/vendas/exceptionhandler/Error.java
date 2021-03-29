package br.com.tirabassi.api.vendas.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private OffsetDateTime dataHora;
    private Integer status;
    private String titulo;
    private String mensagem;

}
