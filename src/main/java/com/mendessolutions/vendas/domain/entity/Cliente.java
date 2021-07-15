package com.mendessolutions.vendas.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome", length = 100)
	@NotEmpty(message="Campo nome é obrigatório.")
	private String nome;
	
	@Column(name = "cpf", length=11)
	@NotEmpty(message = "Campo cpf é obrigatorio.")
	@CPF(message = "Informe um cpf valido.")
	private String cpf;

	@JsonIgnore
	@OneToMany(mappedBy="cliente")
	private Set<Pedido> pedidos;
	
	public Set<Pedido> getPedidos() {
		return pedidos;
	}


	



	


	
	
	
}
