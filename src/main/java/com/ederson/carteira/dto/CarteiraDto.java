package com.ederson.carteira.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CarteiraDto {
	
	private String ticket;
	private Integer quantidade;
	private BigDecimal custo;
	private BigDecimal valorMercado;
	private BigDecimal ganhoNaoRealizado;
	private BigDecimal ganho;
	private BigDecimal cotacao;
	private BigDecimal precoMedio;

}
