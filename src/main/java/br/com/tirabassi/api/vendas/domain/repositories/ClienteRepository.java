package br.com.tirabassi.api.vendas.domain.repositories;

import br.com.tirabassi.api.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeContains(String nome);

    @Query(value = "select * from Cliente where nome like '%:nome%'", nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    boolean existsById(Integer id);

}
