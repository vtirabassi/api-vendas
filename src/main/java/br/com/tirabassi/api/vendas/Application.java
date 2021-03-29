package br.com.tirabassi.api.vendas;

import br.com.tirabassi.api.vendas.domain.entity.Cliente;
import br.com.tirabassi.api.vendas.domain.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {


    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository){

        return args -> {
            Cliente cliente1 = clienteRepository.save(new Cliente("Vinicius"));
            Cliente cliente2 = clienteRepository.save(new Cliente("Sthefanie"));

            clienteRepository.findByNomeContains("Vini").forEach(System.out::println);
            clienteRepository.encontrarPorNome("Vini").forEach(System.out::println);

            clienteRepository.findByNomeOrIdOrderById("Vinicius", 10)
            .forEach(System.out::println);

            clienteRepository.deleteById(cliente1.getId());

            clienteRepository.findAll()
                    .forEach(System.out::println);


            System.out.println(clienteRepository.existsById(cliente2.getId()));
            System.out.println(clienteRepository.existsById(cliente1.getId()));

        };
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
