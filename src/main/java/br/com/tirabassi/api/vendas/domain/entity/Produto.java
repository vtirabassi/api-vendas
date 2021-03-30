package br.com.tirabassi.api.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    @NotEmpty(message = "{produto.descricao.obrigatorio}")
    private String descricao;

    @Column(name = "preco_unitario")
    @JsonProperty(value = "preco_unitario")
    @NotNull(message = "{produto.preco.obrigatorio}")
    private BigDecimal preco;
}
