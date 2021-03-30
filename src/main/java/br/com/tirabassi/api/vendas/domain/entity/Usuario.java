package br.com.tirabassi.api.vendas.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "Usuario")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column
    @NotEmpty(message = "{usuario.login.obrigatorio}")
    private String login;

    @Column
    @NotEmpty(message = "{usuario.senha.obrigatorio}")
    private String senha;

    @Column
    private Boolean admin = false;

}
