package br.com.tirabassi.api.vendas.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Error {

    private OffsetDateTime dataHora;
    private Integer status;
    private String mensagem;
    private List<CampoError> camposErrors;

}
