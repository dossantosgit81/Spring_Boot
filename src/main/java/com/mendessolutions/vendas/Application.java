package com.mendessolutions.vendas;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendessolutions.vendas.annotations.Cachorro;
import com.mendessolutions.vendas.service.Animal;

@SpringBootApplication
@RestController
public class Application {
	
	@Cachorro
	private Animal animal;
	
	@Bean("executarAnimal")
	public CommandLineRunner executar() {
		return args -> {
			this.animal.fazerBarulho();
		};
	}
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	@GetMapping("/hello")
	public String helloWord() {
		return applicationName;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
