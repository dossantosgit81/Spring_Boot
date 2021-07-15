package com.mendessolutions.vendas.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mendessolutions.vendas.domain.entity.Cliente;
import com.mendessolutions.vendas.domain.entity.ItemPedido;
import com.mendessolutions.vendas.domain.entity.Pedido;
import com.mendessolutions.vendas.domain.entity.Produto;
import com.mendessolutions.vendas.domain.entity.enums.StatusPedido;
import com.mendessolutions.vendas.domain.repository.Clientes;
import com.mendessolutions.vendas.domain.repository.ItemsPedidos;
import com.mendessolutions.vendas.domain.repository.Pedidos;
import com.mendessolutions.vendas.domain.repository.Produtos;
import com.mendessolutions.vendas.exception.PedidoNaoEncontradoException;
import com.mendessolutions.vendas.exception.RegraNegocioException;
import com.mendessolutions.vendas.rest.dto.ItensPedidoDTO;
import com.mendessolutions.vendas.rest.dto.PedidoDTO;
import com.mendessolutions.vendas.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService{
	
	private final Pedidos repository;
	
	private final  Clientes clientesRepository;
	
	private final  Produtos produtosRepository;
	
	private final ItemsPedidos itemsPedidosRepository;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clientesRepository
			.findById(idCliente)
			.orElseThrow(() -> new RegraNegocioException("Código de cliente não encontrado"));
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.REALIZADO);
		
		List<ItemPedido> itemsPedido = converterItens(pedido, dto.getItens());
		
		pedido.setItens(itemsPedido);
		repository.save(pedido);
		itemsPedidosRepository.saveAll(itemsPedido);
		return pedido;
	}
	
	private List<ItemPedido> converterItens(Pedido pedido, List<ItensPedidoDTO> items) {
		if(items.isEmpty()) {
			throw new RegraNegocioException("Não é possivel realizar um pedido sem itens");
		}
		
		return items
				.stream()
				.map(dto -> {
					Integer idProduto = dto.getProduto();
					Produto produto = produtosRepository
						.findById(idProduto)
						.orElseThrow(()-> new RegraNegocioException("Código "+idProduto+" de produto inválido "));
					
					ItemPedido itemPedido = new ItemPedido();
					itemPedido.setQuantidade(dto.getQuantidade());
					itemPedido.setPedido(pedido);
					itemPedido.setProduto(produto);
					 return itemPedido;
				}).collect(Collectors.toList());
		
		
	}

		@Override
	    public Optional<Pedido> obterPedidoCompleto(Integer id) {
	        return repository.findByIdFetchItens(id);
	    }

		@Override
		@Transactional
		public void atualizaStatus(Integer id, StatusPedido statusPedido) {
			repository
				.findById(id)
				.map(pedido -> {
					pedido.setStatus(statusPedido);
					return repository.save(pedido);
				}).orElseThrow(()->new PedidoNaoEncontradoException());
		}

}
	
	


