package com.mendessolutions.vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendessolutions.vendas.domain.entity.Pedido;

public interface Pedidos  extends JpaRepository<Pedido, Integer>{

}
