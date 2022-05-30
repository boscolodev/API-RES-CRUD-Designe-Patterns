package com.dev.boscolo.Interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dev.boscolo.Entities.Address;


/**
 * Cliente HTTP, criado via <b>OpenFeign</b>, para o consumo da API do <b>ViaCEP</b>.
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud<a/>
 * @see <a href="https://viacep.com.br">ViaCEP<a/>
 * 
 * 
 * @author Guilherme Boscolo
 *
 */

@FeignClient(name= "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepServiceInterface {

	@GetMapping("/{cep}/json/")
	Address findByPostalCode(@PathVariable("cep") String postalCode);
	
}
