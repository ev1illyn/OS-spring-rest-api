package com.algaworks.osticket.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrdemServicoInput {

	@NotBlank
	private ClienteIdInput cliente;
	
	@NotNull
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
}
