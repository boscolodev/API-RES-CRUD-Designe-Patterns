package com.dev.boscolo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * Projeto Spring Boot gerado via Spring Initializr
 * Módulos selecionados
 * - Spring Data JPA
 * - Spring Web
 * - H2 Database
 * - OpenFeign
 * 
 * @author Guilherme Boscolo
 *
 */

@EnableFeignClients
@SpringBootApplication
public class DesafioPadraoDeProjetoDioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioPadraoDeProjetoDioApplication.class, args);
	}

}
