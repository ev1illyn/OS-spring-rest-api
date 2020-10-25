package com.algaworks.osticket.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osticket.domain.exception.NegocioException;
import com.algaworks.osticket.domain.model.Cliente;
import com.algaworks.osticket.domain.repository.ClienteRepository;

// componente do spring
@Service
public class CadastroClienteService {

	// instanciando o repositório
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente adicionar(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Não foi possível criar o cadastro porque"
					+ " já existe um cliente com esse e-mail. Tente novamente!");
		}
		
		return clienteRepository.save(cliente);
	}

	public void remover(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}
