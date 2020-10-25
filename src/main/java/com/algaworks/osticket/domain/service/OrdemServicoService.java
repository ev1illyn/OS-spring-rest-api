package com.algaworks.osticket.domain.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osticket.domain.exception.NegocioException;
import com.algaworks.osticket.domain.model.Cliente;
import com.algaworks.osticket.domain.model.OrdemServico;
import com.algaworks.osticket.domain.model.StatusOrdemServico;
import com.algaworks.osticket.domain.repository.ClienteRepository;
import com.algaworks.osticket.domain.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {

	// instanciando o repositório
	@Autowired
	private ClienteRepository clienteRepository;

	// instanciando o repositório
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	public OrdemServico adicionar(OrdemServico ordemServico) {

		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado. Tente novamente!"));
		
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDateTime.now());

		return ordemServicoRepository.save(ordemServico);
	}

	public void remover(Long ordemServicoId) {
		ordemServicoRepository.deleteById(ordemServicoId);
	}

}
