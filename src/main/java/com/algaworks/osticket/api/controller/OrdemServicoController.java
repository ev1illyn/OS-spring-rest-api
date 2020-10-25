package com.algaworks.osticket.api.controller;

import java.util.List;

import javax.validation.Valid;

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

	@GetMapping
	public List<OrdemServico> listar(){
		return ordemServicoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico adicionar(@Valid @RequestBody OrdemServico ordemServico) {
		
		return ordemServicoService.adicionar(ordemServico);
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

}
