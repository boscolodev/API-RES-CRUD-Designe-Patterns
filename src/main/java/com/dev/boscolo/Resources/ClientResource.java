package com.dev.boscolo.Resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev.boscolo.Services.ClientService;
import com.dev.boscolo.DTO.ClientDTO;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda a complexidade de integrações
 * (Banco H2, e API do ViaCEP) em uma interface simples e coesa (API REST)
 * 
 * @author Guilherme Boscolo
 *
 */

@RestController
@RequestMapping(value ="clients")
public class ClientResource {

	@Autowired
	ClientService clientService;
	
	// Buscar todos dados páginados
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable){
		Page<ClientDTO> list = clientService.findAllPaged(pageable);

		return ResponseEntity.ok().body(list);
	}

	// Buscar por ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		ClientDTO clientDTO = clientService.findById(id);
		return ResponseEntity.ok().body(clientDTO);

	}

	// Insert de Dados
	@PostMapping
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO clientDTO) {
		clientDTO = clientService.insert(clientDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientDTO.getId()).toUri();

		return ResponseEntity.created(uri).body(clientDTO);
	}

	// Atualização de Dados
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
		clientDTO = clientService.update(id, clientDTO);
		return ResponseEntity.ok().body(clientDTO);
	}

	// Deleção de Dados
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
