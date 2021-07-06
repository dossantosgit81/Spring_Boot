package com.mendessolutions.vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mendessolutions.vendas.domain.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {
 
	//*Query methods*
	
	@Query(value = " select * from cliente c where c.nome like '%:nome%' ",nativeQuery = true)
	List<Cliente> encontrarPornome(@Param(value = "nome") String nome);
	
	List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);
	
	Cliente findOneByNome(String nome);
	
	@Query(" delete from Cliente c where c.nome = :nome")
	//Dizendo para o spring que farei alterações nesse metodo
	@Modifying
	void deleteByNome(String nome);
	
	boolean existsByNome(String nome);
	
    @Query("select c from Cliente c join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos( @Param("id") Integer id );
	
	
	
}
