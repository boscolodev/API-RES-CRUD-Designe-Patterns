package com.dev.boscolo.DTO;

import com.dev.boscolo.Entities.Address;
import com.dev.boscolo.Entities.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

	private Long id;
	private String nome;
	private String email;
	private Address endereco;
	private Boolean status;
	
	public ClientDTO(Client client) {
		this.id = client.getId();
		this.nome = client.getNome();
		this.email = client.getEmail();
		this.endereco = client.getEndereco();
		this.status = client.getStatus();
	}

	
}
