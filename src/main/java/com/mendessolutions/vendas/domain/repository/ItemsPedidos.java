package com.mendessolutions.vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendessolutions.vendas.domain.entity.ItemPedido;

public interface ItemsPedidos extends JpaRepository<ItemPedido, Integer>{

}
