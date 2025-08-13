package com.ederson.stockportfolio.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StockPortfolioDto {
	
	private String ticket;
	private Integer amount;
	private BigDecimal cust;
	private BigDecimal marketValue;
	private BigDecimal unrealizedGain;
	private BigDecimal realizedGain;
	private BigDecimal price;
	private BigDecimal averagePrice;

}
