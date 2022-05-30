package com.dev.boscolo.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.boscolo.DTO.ClientDTO;
import com.dev.boscolo.Entities.Address;
import com.dev.boscolo.Entities.Client;
import com.dev.boscolo.Interfaces.ClientServiceInterface;
import com.dev.boscolo.Interfaces.ViaCepServiceInterface;
import com.dev.boscolo.Repositories.AddressRepository;
import com.dev.boscolo.Repositories.ClientRepository;
import com.dev.boscolo.Services.Exceptions.DatabaseException;
import com.dev.boscolo.Services.Exceptions.ResourceNotFoundException;

/**
 * Implementação da <b>Strategy</b> {@link ClienteServiceInterface}, a qual pode
 * ser injetada pelo spring {@link Autowired}. Com isso como essa classe é um
 * {@linkplain Service}, ela será tratada como um objeto <b>Singleton</b>
 * 
 * 
 * @author Guilherme BoscoloBoscolo
 *
 */

@Service
public class ClientService implements ClientServiceInterface {

	// Singletown Designe Pattern
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ViaCepServiceInterface viaCepService;

	@Override
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(Pageable pageable) {
		Page<Client> list = clientRepository.findAll(pageable);
		return list.map(x -> new ClientDTO(x));
	}

	@Override
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> clientList = clientRepository.findById(id);
		Client client = clientList.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
		return new ClientDTO(client);
	}

	@Override
	@Transactional
	public ClientDTO insert(ClientDTO clientDTO) {

		Address address = FindAndSaveAddress(clientDTO);

		Client client = new Client();
		client.setNome(clientDTO.getNome());
		client.setEmail(clientDTO.getEmail());
		client.setEndereco(clientDTO.getEndereco());
		client.setStatus(clientDTO.getStatus());

		client = clientRepository.save(client);
		return new ClientDTO(client);
	}

	@Transactional
	@Override
	public ClientDTO update(Long id, ClientDTO clientDTO) {

		// Buscar cliente por Id caso existir
		try {

			Address address = FindAndSaveAddress(clientDTO);

			Client client = clientRepository.getReferenceById(id);
			client.setId(id);
			client.setNome(clientDTO.getNome());
			client.setEmail(clientDTO.getEmail());
			client.setEndereco(address);
			client.setStatus(clientDTO.getStatus());

			client = clientRepository.saveAndFlush(client);
			return new ClientDTO(client);

		} catch (javax.persistence.EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id Not Found: " + id);
		}

	}

	@Transactional
	@Override
	public void delete(Long id) {
		try {
			clientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id Not Found: " + id);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity Violation");
		}

	}

	private Address FindAndSaveAddress(ClientDTO clientDTO) {
		// Verificar se o Endereço do Cliente já esxiste (pelo CEP)
		String postalCode = clientDTO.getEndereco().getCep();
		Address address = addressRepository.findById(postalCode).orElseGet(() -> {
			// Caso não exista, integrar como ViaCEP e persistir o retorno
			Address addressNew = viaCepService.findByPostalCode(postalCode);
			addressRepository.save(addressNew);
			clientDTO.setEndereco(addressNew);
			return addressNew;
		});
		return address;
	}

}
