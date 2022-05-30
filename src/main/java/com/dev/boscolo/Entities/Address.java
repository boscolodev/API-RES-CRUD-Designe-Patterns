package com.dev.boscolo.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *	
 *	Atributos gerado automáticamente pelosite jsonschema2pojo.org.
 *	Para isso usamos o json de retorno da API do ViaCEP
 *
 * @see <a href="https://www.jsonschema2pojo.org">jsonschema2pojo.org</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 * 
 * 
 * @author Guilherme Boscolo
 *
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String ibge;
	private String gia;
	private String ddd;
	private String siafi;
	
	
}