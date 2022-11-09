package com.ederson.carteira.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalheCarteiraDto {
	
	private String ticket;
    private Long quantidade;
    private BigDecimal custo;
    private BigDecimal valorMercado;
    private BigDecimal precoMedio;
    private BigDecimal ganho;
    private BigDecimal ganhoNaoRealizado;
    private BigDecimal cotacao;
    private BigDecimal dataAtualizacaoCota;
    
}
