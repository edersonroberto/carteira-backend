package com.ederson.stockportfolio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ederson.stockportfolio.enums.OperationType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalheNotaDto {

	private String ticket;
	
	private String nomeCorretora;

	private LocalDate data;

	private OperationType tipoOperacao;

	private Integer quantidade;

	private BigDecimal valor;
	
	private BigDecimal taxa;
	
	private BigDecimal emolumento;

	private BigDecimal custoTotal;

}
