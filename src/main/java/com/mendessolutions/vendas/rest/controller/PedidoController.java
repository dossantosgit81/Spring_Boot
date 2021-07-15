package com.mendessolutions.vendas.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mendessolutions.vendas.domain.entity.ItemPedido;
import com.mendessolutions.vendas.domain.entity.Pedido;
import com.mendessolutions.vendas.domain.entity.enums.StatusPedido;
import com.mendessolutions.vendas.rest.dto.AtualizacaoEstadoPedidoDTO;
import com.mendessolutions.vendas.rest.dto.InformacaoPedidoDTO;
import com.mendessolutions.vendas.rest.dto.InformacoesPedidoDTO;
import com.mendessolutions.vendas.rest.dto.PedidoDTO;
import com.mendessolutions.vendas.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidoService service;

	public PedidoController(PedidoService service) {
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody PedidoDTO dto) {
		Pedido pedido = service.salvar(dto);
		return pedido.getId();
	}
	
	@GetMapping("{id}")
	public InformacoesPedidoDTO getById(@PathVariable Integer id) {
		return service
				.obterPedidoCompleto(id)
				.map(p->converter(p))
				.orElseThrow(()->
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
	}
	
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoEstadoPedidoDTO dto) {
		String novoStatus = dto.getNovoStatus();
		service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
	}
	
	private InformacoesPedidoDTO converter(Pedido pedido) {
		return InformacoesPedidoDTO
		.builder()
		.codigo(pedido.getId())
		.dataPedido(pedido.getDataPedido()
		.format(DateTimeFormatter.ofPattern("dd//MM/yyyy")))
		.cpf(pedido.getCliente().getCpf())
		.nomeCliente(pedido.getCliente().getNome())
		.total(pedido.getTotal())
		.Status(pedido.getStatus().name())
		.itens(converter(pedido.getItens()))	
		.build();
	}
	
	private List<InformacaoPedidoDTO> converter(List<ItemPedido> itens){
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens
				.stream()
				.map(
				item -> InformacaoPedidoDTO
				.builder()
				.descricaoProduto(item.getProduto().getDescricao())
				.precounitario(item.getProduto().getPreco())
				.build()).collect(Collectors.toList());
	}

	
}
