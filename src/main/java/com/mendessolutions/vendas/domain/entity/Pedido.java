package com.mendessolutions.vendas.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@Column(name="data_pedido")
	private LocalDate dataPedido;
	
	@Column(name="total", scale=2, precision=20)
	private BigDecimal total;
	
	@OneToMany(mappedBy="pedido"  ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ItemPedido> itens;
	
	public List<ItemPedido> getItens() {
		return itens;
	}
	
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public LocalDate getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", cliente=" + cliente + ", dataPedido=" + dataPedido + ", total=" + total
				+ "  ]";
	}
	
	
	
}
