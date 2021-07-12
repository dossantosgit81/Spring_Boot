package com.mendessolutions.vendas.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome", length = 100)
	private String nome;
	
	@Column(length=11)
	private String cpf;
		
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@JsonIgnore
	@OneToMany(mappedBy="cliente")
	private Set<Pedido> pedidos;
	
	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	
	public Cliente() {
		
	}
	
	public Cliente(String nome, Integer id) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + "]";
	}

	
	
	
}
