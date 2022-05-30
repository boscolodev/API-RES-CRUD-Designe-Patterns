package com.dev.boscolo.Interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dev.boscolo.DTO.ClientDTO;


/**
 * Interface que define o padrão <b>Strategy</b> no domínio cliente. Com isso se necessário podemos
 * ter múltiplas implementações dessa mesma interface.
 * 
 * @author Guilherme Boscolo
 *
 */

public interface ClientServiceInterface {
	
	Page<ClientDTO> findAllPaged(Pageable pageable);
	
	ClientDTO findById(Long id);
	
	ClientDTO insert(ClientDTO client);
	ClientDTO update(Long id, ClientDTO client);
	void delete(Long id);

	

}
