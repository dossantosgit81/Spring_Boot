package com.mendessolutions.vendas.rest.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mendessolutions.vendas.domain.entity.Cliente;
import com.mendessolutions.vendas.domain.repository.Clientes;

@Controller
public class ClienteController {
	
	private Clientes clientes;
	
	public ClienteController(Clientes clientes) {
		this.clientes = clientes;
	}

	@GetMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/api/clientes")
	@ResponseBody
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = clientes.save(cliente);
		return ResponseEntity.ok(clienteSalvo);
	}
	
	@DeleteMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> delete(@PathVariable Integer id) {
		
		Optional<Cliente> cliente = clientes.findById(id);
			if(cliente.isPresent()) {	
			clientes.delete(cliente.get());
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity update(@RequestBody Cliente cliente, 
			@PathVariable Integer id ) {
		return clientes
				.findById(id)
				.map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			clientes.save(cliente);
			return ResponseEntity.noContent().build();
			
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
	
}
