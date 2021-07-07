package com.mendessolutions.vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendessolutions.vendas.domain.entity.Cliente;
import com.mendessolutions.vendas.domain.entity.Pedido;

public interface Pedidos  extends JpaRepository<Pedido, Integer>{
	
	List<Pedido> findByCliente(Cliente cliente);

}
