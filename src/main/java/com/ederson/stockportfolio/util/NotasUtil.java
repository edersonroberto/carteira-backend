package com.ederson.stockportfolio.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

import com.ederson.stockportfolio.enums.OperationType;
import com.ederson.stockportfolio.model.Nota;

public class NotasUtil {

	private static long cotasCompradas(List<Nota> notas) {

		return notas.stream().filter(nota -> nota.getOperationType().equals(OperationType.COMPRA))
				.map(nota -> nota.getAmount()).reduce(0, Integer::sum);
	}

	private static long cotasVendidas(List<Nota> notas) {

		return notas.stream().filter(nota -> nota.getOperationType().equals(OperationType.VENDA))
				.map(nota -> nota.getAmount()).reduce(0, Integer::sum);
	}
	
	public static long cotasEmCarteira(List<Nota> notas) {

		return cotasCompradas(notas) - cotasVendidas(notas);
	}
	
	public static BigDecimal custoTotal(List<Nota> notas) {
		
		return notas.stream().filter(nota -> nota.getOperationType().equals(OperationType.COMPRA))
				.map(nota -> nota.getValue().multiply(new BigDecimal(nota.getAmount()))
				.add(nota.getTaxa()).add(nota.getExchangeFee()))
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
		return notas.stream().filter(nota -> nota.getOperationType().equals(OperationType.VENDA))
				.map(nota -> nota.getValue().multiply(new BigDecimal(nota.getAmount()))
				.subtract(nota.getTaxa()).subtract(nota.getExchangeFee()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	public static BigDecimal ganhoComDividendos(List<Nota> notas) {
		return notas.stream().filter(nota -> nota.getOperationType().equals(OperationType.DIVIDENDO))
				.map(nota -> nota.getValue().multiply(new BigDecimal(nota.getAmount())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
	}

	public static BigDecimal menorPreco(List<Nota> notas) {
		return notas.stream().filter(nota -> nota.getOperationType().equals(OperationType.COMPRA))
				.map(nota -> nota.getValue())
				.min(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
	}
	
	public static BigDecimal maiorPreco(List<Nota> notas) {
		return notas.stream().filter(nota -> nota.getOperationType().equals(OperationType.COMPRA))
				.map(nota -> nota.getValue())
				.max(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
	}

}
