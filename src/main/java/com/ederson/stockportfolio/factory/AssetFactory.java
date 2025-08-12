package com.ederson.stockportfolio.factory;

import java.math.BigDecimal;
import java.util.List;

import com.ederson.stockportfolio.dto.AssetDto;
import com.ederson.stockportfolio.dto.AssetDetailDto;
import com.ederson.stockportfolio.dto.TicketDto;
import com.ederson.stockportfolio.model.Asset;
import com.ederson.stockportfolio.model.TradeConfirmation;
import com.ederson.stockportfolio.util.TradingConfirmationUtil;

public class AssetFactory {

	public TicketDto toTicketDto(Asset a) {
		return TicketDto.builder()
				.id(a.getId())
				.name(a.getTicket())
				.build();
	}

	public Asset toAtivo(AssetDto ativoDto) {
		return Asset.builder()
				.cnpj(ativoDto.getCnpj())
				.name(ativoDto.getName())
				.ticket(ativoDto.getTicket())
				.build();
	}

	public AssetDetailDto toDetalheAtivo(List<TradeConfirmation> notas) {
		String ticket = notas.get(0).getAsset().getTicket();
		
		long quantidade = TradingConfirmationUtil.cotasEmCarteira(notas);
		BigDecimal custoTotal = TradingConfirmationUtil.custoTotal(notas);
		BigDecimal precoMedio = TradingConfirmationUtil.precoMedio(custoTotal, quantidade);
		BigDecimal ganhoRealizado = TradingConfirmationUtil.ganhoRealizado(notas);
		BigDecimal ganhoComDividendo = TradingConfirmationUtil.ganhoComDividendos(notas);
		BigDecimal menorPreco = TradingConfirmationUtil.menorPreco(notas);
		BigDecimal maiorPreco = TradingConfirmationUtil.maiorPreco(notas);
		
		return AssetDetailDto.builder()
				.ticket(ticket)
				.amount(quantidade)
				.tipoAtivo(notas.get(0).getAsset().getTipoAtivo().getDescricao())
				.lowerPrice(menorPreco)
				.highPrice(maiorPreco)
				.custoTotal(custoTotal)
				.precoMedio(precoMedio)
				.ganhoRealizado(ganhoRealizado)
				.ganhoComDividendo(ganhoComDividendo)
				.notas(notas)
				.build();
	}

	public List<AssetDetailDto> toDetalheCarteiraDto(Asset ativo) {
		return null;
	}

}
