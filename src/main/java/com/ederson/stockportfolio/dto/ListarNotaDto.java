package com.ederson.stockportfolio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ederson.stockportfolio.enums.OperationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListarNotaDto {
	
	private Long id;
	
	private Long idCorretora;
	
	private String ticket;
	
	private LocalDate data;

	private OperationType tipoOperacao;

	private Integer quantidade;

	private BigDecimal valor;

	private BigDecimal taxa;
	
	private BigDecimal emolumento;
	
	private BigDecimal corretagem;
	
	private BigDecimal iss;
	

}
