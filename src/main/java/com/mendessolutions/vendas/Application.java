package com.mendessolutions.vendas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mendessolutions.vendas.domain.entity.Cliente;
import com.mendessolutions.vendas.domain.entity.Pedido;
import com.mendessolutions.vendas.domain.repository.Clientes;
import com.mendessolutions.vendas.domain.repository.Pedidos;

@SpringBootApplication
public class Application {
	
	@Bean
	public CommandLineRunner init (@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
		return args -> {
			System.out.println("Salvando clientes");
			clientes.save(new Cliente("Rafael", null));
			Cliente douglas = clientes.save(new Cliente("Douglas", null));
			System.out.println(douglas);
			
			Pedido p = new Pedido();
			p.setCliente(douglas);
			p.setDataPedido( LocalDate.now() );
			p.setTotal(BigDecimal.valueOf(100.00));
			pedidos.save(p);
			
			Cliente clienteProcurado = clientes.findClienteFetchPedidos(douglas.getId());
			
			System.out.println("Aqui o cliente procurado"+ clienteProcurado);
			System.out.println(clienteProcurado.getPedidos());
			
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
