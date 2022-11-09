package com.ederson.carteira.factory;

import java.math.BigDecimal;
import java.util.List;

import com.ederson.carteira.dto.AtivoDto;
import com.ederson.carteira.dto.DetalheAtivoDto;
import com.ederson.carteira.dto.TicketDto;
import com.ederson.carteira.model.Ativo;
import com.ederson.carteira.model.Nota;
import com.ederson.carteira.util.NotasUtil;

public class AtivoFactory {

	public TicketDto toTicketDto(Ativo a) {
		return TicketDto.builder()
				.id(a.getId())
				.nome(a.getTicket())
				.build();
	}

	public Ativo toAtivo(AtivoDto ativoDto) {
		return Ativo.builder()
				.cnpj(ativoDto.getCnpj())
				.nome(ativoDto.getNome())
				.ticket(ativoDto.getTicket())
				.build();
	}

	public DetalheAtivoDto toDetalheAtivo(List<Nota> notas) {
		String ticket = notas.get(0).getAtivo().getTicket();
		
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
				.tipoAtivo(notas.get(0).getAtivo().getTipoAtivo().getDescricao())
				.menorPreco(menorPreco)
				.maiorPreco(maiorPreco)
				.custoTotal(custoTotal)
				.precoMedio(precoMedio)
				.ganhoRealizado(ganhoRealizado)
				.ganhoComDividendo(ganhoComDividendo)
				.notas(notas)
				.build();
	}

	public List<DetalheAtivoDto> toDetalheCarteiraDto(Ativo ativo) {
		return null;
	}

}
