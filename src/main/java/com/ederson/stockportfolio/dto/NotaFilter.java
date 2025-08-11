package com.ederson.stockportfolio.dto;

import java.time.LocalDate;

import com.ederson.stockportfolio.enums.OperationType;

import lombok.Data;

@Data
public class NotaFilter {

	private Long idAtivo;
	
	private Long idCorretora;
	
	private OperationType tipoOperacao;
	
	private LocalDate dataOperacao;

}
