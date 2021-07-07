package com.mendessolutions.vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mendessolutions.vendas.domain.entity.Cliente;
import com.mendessolutions.vendas.domain.repository.Clientes;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	private Clientes clientes;	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cliente cliente = new Cliente("Fulano", null);
		clientes.save(cliente);
		
	}

}
