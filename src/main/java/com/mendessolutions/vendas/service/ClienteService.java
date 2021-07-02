package com.mendessolutions.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendessolutions.vendas.model.Cliente;
import com.mendessolutions.vendas.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private ClienteRepository repository;
	
	@Autowired
	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}
	
	
// Posaso omitir o @Autowired. Pois o spring já entende que essa classe precisará de uma instancia
//	public ClienteService(ClienteRepository repository) {
//		this.repository = repository;
//	}
	
	//Forma de injetat depedencia pelo set
//	@Autowired	
//	public ClienteRepository getRepository() {
//		return repository;
//	}



	public void setRepository(ClienteRepository repository) {
		this.repository = repository;
	}



	public void salvarCliente(Cliente cliente) {
		validarCliente(cliente);
		this.repository.persistir(cliente);
	}
	
	public void validarCliente(Cliente cliente) {
		//Aplica validações
	}
	
}
