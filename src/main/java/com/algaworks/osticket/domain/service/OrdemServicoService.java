package com.algaworks.osticket.domain.service;

import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osticket.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.osticket.domain.exception.NegocioException;
import com.algaworks.osticket.domain.model.Cliente;
import com.algaworks.osticket.domain.model.Comentario;
import com.algaworks.osticket.domain.model.OrdemServico;
import com.algaworks.osticket.domain.model.StatusOrdemServico;
import com.algaworks.osticket.domain.repository.ClienteRepository;
import com.algaworks.osticket.domain.repository.ComentarioRepository;
import com.algaworks.osticket.domain.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {

	// instanciando o repositório
	@Autowired
	private ClienteRepository clienteRepository;

	// instanciando o repositório
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	// instanciando o repositório
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico adicionar(OrdemServico ordemServico) {

		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado. Tente novamente!"));

		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());

		return ordemServicoRepository.save(ordemServico);
	}

	public void remover(Long ordemServicoId) {
		ordemServicoRepository.deleteById(ordemServicoId);
	}

	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException ("Ordem de serviço não encontrado. Tente novamente!"));
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(comentario);
	}
	
}
