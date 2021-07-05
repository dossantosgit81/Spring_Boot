package com.mendessolutions.vendas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mendessolutions.vendas.domain.entity.Cliente;
import com.mendessolutions.vendas.domain.repository.Clientes;

@SpringBootApplication
public class Application {
	
	@Bean
	public CommandLineRunner init (@Autowired Clientes clientes) {
		return args -> {
			System.out.println("Salvando clientes");
			clientes.save(new Cliente("Rafael", null));
			clientes.save(new Cliente("Douglas", null));
			
			System.out.println("Listagem clientes");
			List<Cliente> todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);
			
			System.out.println("Existe cliente com esse nmome:");
			boolean existeCliente = clientes.existsByNome("Douglas");
			System.out.println(existeCliente);
			
			System.out.println("Busca de clientes");
			List<Cliente> result = clientes.encontrarPornome("Rafael");
			result.forEach(System.out::println);
			
		};
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
