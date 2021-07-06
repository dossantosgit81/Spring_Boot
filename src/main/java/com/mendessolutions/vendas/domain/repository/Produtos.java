package com.mendessolutions.vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendessolutions.vendas.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer> {

}
