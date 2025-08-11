package com.ederson.stockportfolio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ederson.stockportfolio.enums.OperationType;

import lombok.Data;

@Data
public class NotaDto {

	private Long idAtivo;
	
	private Long idCorretora;

	private LocalDate data;

	private OperationType tipoOperacao;

	private Integer quantidade;

	private BigDecimal valor;
	
	private BigDecimal taxa;
	
	private BigDecimal emolumento;
	
	private BigDecimal corretagem;
	
	private BigDecimal iss;
	
	public BigDecimal getValorTotal() {
		return this.getValor().multiply(new BigDecimal(this.getQuantidade()));
	}

	//private BigDecimal taxa;

	//public BigDecimal getCustoTotal() {

	//	return this.getValor().multiply(new BigDecimal(this.getQuantidade())).add(this.getTaxa());
	//}

}
