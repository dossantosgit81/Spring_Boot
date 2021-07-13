package com.mendessolutions.vendas.service;

import com.mendessolutions.vendas.domain.entity.Pedido;
import com.mendessolutions.vendas.rest.dto.PedidoDTO;

public interface PedidoService {

	Pedido salvar (PedidoDTO dto);
	
}
