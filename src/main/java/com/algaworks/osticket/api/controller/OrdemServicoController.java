package com.algaworks.osticket.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osticket.api.model.OrdemServicoInput;
import com.algaworks.osticket.api.model.OrdemServicoModel;
import com.algaworks.osticket.domain.model.Cliente;
import com.algaworks.osticket.domain.model.OrdemServico;
import com.algaworks.osticket.domain.repository.OrdemServicoRepository;
import com.algaworks.osticket.domain.service.OrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<OrdemServicoModel> listar(){
		return toCollectionModel(ordemServicoRepository.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoModel adicionar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
		
		OrdemServico ordemServico = toEntity(ordemServicoInput);
	
		return toModel(ordemServicoService.adicionar(ordemServico));
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long ordemServicoId) {

		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		
		if (ordemServico.isPresent()) {
			OrdemServicoModel ordemServicoModel = toModel(ordemServico.get());
			return ResponseEntity.ok(ordemServicoModel);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServico> atualizar(@Valid @PathVariable Long ordemServicoId,
			@RequestBody OrdemServico ordemServico) {
		
		if (!ordemServicoRepository.existsById(ordemServicoId)) {
			return ResponseEntity.notFound().build();
		}
		ordemServico.setId(ordemServicoId);
		ordemServico = ordemServicoService.adicionar(ordemServico);
		
		return ResponseEntity.ok(ordemServico);
		
	}
	
	@DeleteMapping("/{ordemServicoId}")
	public ResponseEntity<Cliente> remover(@PathVariable Long ordemServicoId) {
		
		if (!ordemServicoRepository.existsById(ordemServicoId)) {
			return ResponseEntity.notFound().build();
		}
		ordemServicoService.remover(ordemServicoId);
		
		return ResponseEntity.noContent().build();
	}
	
	/* modelMapper vai instanciar o OrdemServicoModel e 
	 * mapear/atribuir as propriedades do model OrdemServico */
	private OrdemServicoModel toModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoModel.class);
	}
	
	/* retorna uma lista de OrdemServicoModel*/
	private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> ordensServico) {
		return ordensServico.stream()
				.map(ordemServico -> toModel(ordemServico))
				.collect(Collectors.toList());
	}
	
	private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
	}
	
}
