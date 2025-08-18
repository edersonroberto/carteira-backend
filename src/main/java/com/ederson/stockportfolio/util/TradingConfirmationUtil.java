package com.ederson.stockportfolio.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

import com.ederson.stockportfolio.enums.OperationType;
import com.ederson.stockportfolio.model.TradeConfirmation;

public class TradingConfirmationUtil {

	private static long cotasCompradas(List<TradeConfirmation> tradingConfirmations) {

		return tradingConfirmations.stream()
				.filter(tradingConfirmation -> tradingConfirmation.getOperationType().equals(OperationType.BUY))
				.map(nota -> nota.getAmount()).reduce(0, Integer::sum);
	}

	private static long cotasVendidas(List<TradeConfirmation> tradingConfirmations) {

		return tradingConfirmations.stream()
				.filter(tradingConfirmation -> tradingConfirmation.getOperationType().equals(OperationType.SALE))
				.map(nota -> nota.getAmount()).reduce(0, Integer::sum);
	}

	public static long cotasEmCarteira(List<TradeConfirmation> tradingConfirmations) {

		return cotasCompradas(tradingConfirmations) - cotasVendidas(tradingConfirmations);
	}

	public static BigDecimal custoTotal(List<TradeConfirmation> tradingConfirmations) {

		return tradingConfirmations.stream().filter(nota -> nota.getOperationType().equals(OperationType.BUY))
				.map(nota -> nota.getValue().multiply(new BigDecimal(nota.getAmount())).add(nota.getClearingFee())
						.add(nota.getExchangeFee()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public static BigDecimal precoMedio(BigDecimal totalCost, long amount) {
		BigDecimal averagePrice = BigDecimal.ZERO;
		if (amount > 0) {
			averagePrice = totalCost.divide(new BigDecimal(amount), 2, RoundingMode.HALF_UP);
		}
		return averagePrice;
	}

	public static BigDecimal ganhoRealizado(List<TradeConfirmation> tradingConfirmations) {
		return tradingConfirmations.stream()
				.filter(tradingConfirmation -> tradingConfirmation.getOperationType().equals(OperationType.SALE))
				.map(tradingConfirmation -> tradingConfirmation.getValue().multiply(new BigDecimal(tradingConfirmation.getAmount())).subtract(tradingConfirmation.getClearingFee())
						.subtract(tradingConfirmation.getExchangeFee()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public static BigDecimal ganhoComDividendos(List<TradeConfirmation> tradingConfirmations) {
		return tradingConfirmations.stream().filter(tradingConfirmation -> tradingConfirmation.getOperationType().equals(OperationType.DIVIDEND))
				.map(tradingConfirmation -> tradingConfirmation.getValue().multiply(new BigDecimal(tradingConfirmation.getAmount())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

	}

	public static BigDecimal menorPreco(List<TradeConfirmation> tradingConfirmations) {
		return tradingConfirmations.stream().filter(nota -> nota.getOperationType().equals(OperationType.BUY))
				.map(tradingConfirmation -> tradingConfirmation.getValue()).min(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
	}

	public static BigDecimal maiorPreco(List<TradeConfirmation> tradingConfirmations) {
		return tradingConfirmations.stream().filter(tradingConfirmation -> tradingConfirmation.getOperationType().equals(OperationType.BUY))
				.map(tradingConfirmation -> tradingConfirmation.getValue()).max(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
	}

}
