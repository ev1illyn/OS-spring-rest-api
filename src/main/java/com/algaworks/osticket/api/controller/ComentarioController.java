package com.algaworks.osticket.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osticket.api.model.ComentarioInput;
import com.algaworks.osticket.api.model.ComentarioModel;
import com.algaworks.osticket.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.osticket.domain.model.Comentario;
import com.algaworks.osticket.domain.model.OrdemServico;
import com.algaworks.osticket.domain.repository.OrdemServicoRepository;
import com.algaworks.osticket.domain.service.OrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@GetMapping
	public List<ComentarioModel> listar(@PathVariable Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Nenhum comentário. Tente novamente!"));
		
		return toCollectionModel(ordemServico.getComentarios());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long ordemServicoId,
			@Valid @RequestBody ComentarioInput comentarioInput) {
		Comentario comentario = ordemServicoService.adicionarComentario(ordemServicoId,
				comentarioInput.getDescricao());
		return toModel(comentario);
	}

	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
	
	/* retorna uma lista de ComentarioModel*/
	private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
		return comentarios.stream()
				.map(comentario -> toModel(comentario))
				.collect(Collectors.toList());
	}
	
	
}
