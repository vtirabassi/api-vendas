package br.com.tirabassi.api.vendas.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO {

    @NotEmpty(message = "{usuario.login.obrigatorio}")
    public String login;

    @NotEmpty(message = "{usuario.senha.obrigatorio}")
    public String senha;
}
