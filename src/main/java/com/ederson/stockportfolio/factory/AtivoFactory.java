package com.ederson.stockportfolio.factory;

import java.math.BigDecimal;
import java.util.List;

import com.ederson.stockportfolio.dto.AtivoDto;
import com.ederson.stockportfolio.dto.DetalheAtivoDto;
import com.ederson.stockportfolio.dto.TicketDto;
import com.ederson.stockportfolio.model.Asset;
import com.ederson.stockportfolio.model.Nota;
import com.ederson.stockportfolio.util.NotasUtil;

public class AtivoFactory {

	public TicketDto toTicketDto(Asset a) {
		return TicketDto.builder()
				.id(a.getId())
				.nome(a.getTicket())
				.build();
	}

	public Asset toAtivo(AtivoDto ativoDto) {
		return Asset.builder()
				.cnpj(ativoDto.getCnpj())
				.name(ativoDto.getNome())
				.ticket(ativoDto.getTicket())
				.build();
	}

	public DetalheAtivoDto toDetalheAtivo(List<Nota> notas) {
		String ticket = notas.get(0).getAsset().getTicket();
		
		long quantidade = NotasUtil.cotasEmCarteira(notas);
		BigDecimal custoTotal = NotasUtil.custoTotal(notas);
		BigDecimal precoMedio = NotasUtil.precoMedio(custoTotal, quantidade);
		BigDecimal ganhoRealizado = NotasUtil.ganhoRealizado(notas);
		BigDecimal ganhoComDividendo = NotasUtil.ganhoComDividendos(notas);
		BigDecimal menorPreco = NotasUtil.menorPreco(notas);
		BigDecimal maiorPreco = NotasUtil.maiorPreco(notas);
		
		return DetalheAtivoDto.builder()
				.ticket(ticket)
				.quantidade(quantidade)
				.tipoAtivo(notas.get(0).getAsset().getTipoAtivo().getDescricao())
				.menorPreco(menorPreco)
				.maiorPreco(maiorPreco)
				.custoTotal(custoTotal)
				.precoMedio(precoMedio)
				.ganhoRealizado(ganhoRealizado)
				.ganhoComDividendo(ganhoComDividendo)
				.notas(notas)
				.build();
	}

	public List<DetalheAtivoDto> toDetalheCarteiraDto(Asset ativo) {
		return null;
	}

}
