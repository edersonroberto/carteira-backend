package com.ederson.carteira.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ederson.carteira.enums.TipoOperacao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalheNotaDto {

	private String ticket;
	
	private String nomeCorretora;

	private LocalDate data;

	private TipoOperacao tipoOperacao;

	private Integer quantidade;

	private BigDecimal valor;
	
	private BigDecimal taxa;
	
	private BigDecimal emolumento;

	private BigDecimal custoTotal;

}
