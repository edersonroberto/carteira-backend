package com.ederson.stockportfolio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ederson.stockportfolio.enums.OperationType;

import lombok.Data;

@Data
public class TradeConfirmationDto {

	private Long idAtivo;
	
	private Long idCorretora;

	private LocalDate data;

	private OperationType operationType;

	private Integer amount;

	private BigDecimal value;
	
	private BigDecimal taxa;
	
	private BigDecimal emolumento;
	
	private BigDecimal corretagem;
	
	private BigDecimal iss;
	
	public BigDecimal getValorTotal() {
		return this.getValue().multiply(new BigDecimal(this.getAmount()));
	}

	//private BigDecimal taxa;

	//public BigDecimal getCustoTotal() {

	//	return this.getValor().multiply(new BigDecimal(this.getQuantidade())).add(this.getTaxa());
	//}

}
