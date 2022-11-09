package com.ederson.carteira.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

import com.ederson.carteira.enums.TipoOperacao;
import com.ederson.carteira.model.Nota;

public class NotasUtil {

	private static long cotasCompradas(List<Nota> notas) {

		return notas.stream().filter(nota -> nota.getOperacao().equals(TipoOperacao.COMPRA))
				.map(nota -> nota.getQuantidade()).reduce(0, Integer::sum);
	}

	private static long cotasVendidas(List<Nota> notas) {

		return notas.stream().filter(nota -> nota.getOperacao().equals(TipoOperacao.VENDA))
				.map(nota -> nota.getQuantidade()).reduce(0, Integer::sum);
	}
	
	public static long cotasEmCarteira(List<Nota> notas) {

		return cotasCompradas(notas) - cotasVendidas(notas);
	}
	
	public static BigDecimal custoTotal(List<Nota> notas) {
		
		return notas.stream().filter(nota -> nota.getOperacao().equals(TipoOperacao.COMPRA))
				.map(nota -> nota.getValor().multiply(new BigDecimal(nota.getQuantidade()))
				.add(nota.getTaxa()).add(nota.getEmolumento()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public static BigDecimal precoMedio(BigDecimal custoTotal, long quantidade) {
		BigDecimal precoMedio = BigDecimal.ZERO;
		if (quantidade > 0) {			
			precoMedio = custoTotal.divide(new BigDecimal(quantidade), 2, RoundingMode.HALF_UP);
		}
		return precoMedio;
	}

	public static BigDecimal ganhoRealizado(List<Nota> notas) {
		return notas.stream().filter(nota -> nota.getOperacao().equals(TipoOperacao.VENDA))
				.map(nota -> nota.getValor().multiply(new BigDecimal(nota.getQuantidade()))
				.subtract(nota.getTaxa()).subtract(nota.getEmolumento()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	public static BigDecimal ganhoComDividendos(List<Nota> notas) {
		return notas.stream().filter(nota -> nota.getOperacao().equals(TipoOperacao.DIVIDENDO))
				.map(nota -> nota.getValor().multiply(new BigDecimal(nota.getQuantidade())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
	}

	public static BigDecimal menorPreco(List<Nota> notas) {
		return notas.stream().filter(nota -> nota.getOperacao().equals(TipoOperacao.COMPRA))
				.map(nota -> nota.getValor())
				.min(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
	}
	
	public static BigDecimal maiorPreco(List<Nota> notas) {
		return notas.stream().filter(nota -> nota.getOperacao().equals(TipoOperacao.COMPRA))
				.map(nota -> nota.getValor())
				.max(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
	}

}
