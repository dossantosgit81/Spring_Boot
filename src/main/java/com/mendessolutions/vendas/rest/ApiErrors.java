package com.mendessolutions.vendas.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {

	@Getter
	private List<String> errors;
	
	public ApiErrors (String mensagemErrors) {
		this.errors = Arrays.asList(mensagemErrors);
	}
	
}
