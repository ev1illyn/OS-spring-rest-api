package com.algaworks.osticket.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osticket.domain.model.Cliente;
import com.algaworks.osticket.domain.repository.ClienteRepository;

@RestController
public class ClienteController {

	// instanciando o repositório
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
}
