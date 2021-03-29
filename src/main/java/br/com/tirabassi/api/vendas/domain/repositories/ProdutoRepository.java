package br.com.tirabassi.api.vendas.domain.repositories;

import br.com.tirabassi.api.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
