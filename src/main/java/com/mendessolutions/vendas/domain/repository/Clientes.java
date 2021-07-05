package com.mendessolutions.vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendessolutions.vendas.domain.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {

	//*Query methods*
	
	List<Cliente> findByNomeLike(String nome);
	
	List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);
	
	Cliente findOneByNome(String nome);
	
	boolean existsByNome(String nome);
	
	
	
}
