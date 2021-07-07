package com.mendessolutions.vendas.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

	@RequestMapping(value="/hello/{nome}",
			method=RequestMethod.GET, 
			//Forma que eu vou receber os dados
			consumes= {"application/json", "application/xml"},
			//Forma que eu envio os dados
			produces = {"application/json", "application/xml"}
			)
	@ResponseBody
	public String helloCliente(@PathVariable("nome") String nomeCliente) {
		return String.format("Hello %s", nomeCliente);
	}
	
}
