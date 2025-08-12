package com.ederson.stockportfolio.dto;

import java.math.BigDecimal;
import java.util.List;

import com.ederson.stockportfolio.model.TradeConfirmation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetDetailDto {

	private String ticket;
	private Long amount;
	private BigDecimal lowerPrice;
	private BigDecimal highPrice;
	private BigDecimal custoTotal;
	private BigDecimal precoMedio;
	private BigDecimal valorCota;
	private BigDecimal ganhoRealizado;
	private BigDecimal ganhoComDividendo;
	private String tipoAtivo;
	private List<TradeConfirmation> notas;
}
