package com.mendessolutions.vendas.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.mendessolutions.vendas.validation.NotEmptyList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

	@NotNull(message="Informe o código do cliente.")
	private Integer cliente;
	
	@NotNull(message="Informe o total do pedido")
	private BigDecimal total;
	
	@NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
	private List<ItensPedidoDTO> itens;
	
	
}
