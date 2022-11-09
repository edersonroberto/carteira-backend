package com.ederson.carteira.dto;

import java.time.LocalDate;

import com.ederson.carteira.enums.TipoOperacao;

import lombok.Data;

@Data
public class NotaFilter {

	private Long idAtivo;
	
	private Long idCorretora;
	
	private TipoOperacao tipoOperacao;
	
	private LocalDate dataOperacao;

}
