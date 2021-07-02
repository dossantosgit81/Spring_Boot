package com.mendessolutions.vendas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.mendessolutions.vendas.annotations.Development;

/*
 * A anotation @Configuration, serve para informar dados globais para aplicação
 * e o próprio spring, vai escanear as classes com essa anotation
 * 
 * O @Bean, vai justamente inserir um comportamento global para o metodo applicationName
 * 
 * */

@Development
public class MyConfiguration {

	@Bean
	public CommandLineRunner executar() {
		return args -> {
			System.out.println("Rodando config de dev");
		};
	}
	
}
