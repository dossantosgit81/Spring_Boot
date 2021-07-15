package com.mendessolutions.vendas.service;

import java.util.Optional;

import com.mendessolutions.vendas.domain.entity.Pedido;
import com.mendessolutions.vendas.rest.dto.PedidoDTO;

public interface PedidoService {

	Pedido salvar (PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);
	
}
