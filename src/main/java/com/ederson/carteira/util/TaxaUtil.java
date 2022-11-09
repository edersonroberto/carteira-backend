package com.ederson.carteira.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxaUtil {
	
	private static BigDecimal TAXA_LIQUIDACAO = new BigDecimal(0.0275);
	private static BigDecimal EMOLUMENTO = new BigDecimal(0.0031160);
	private static BigDecimal CEM = new BigDecimal(100);
	
	public static BigDecimal calcTaxaLiquidacao(BigDecimal valor) {
		return valor.multiply(TAXA_LIQUIDACAO).divide(CEM)
				.setScale(2, RoundingMode.DOWN);
	}


	public static BigDecimal calcEmolumento(BigDecimal valor) {
		return valor.multiply(EMOLUMENTO).divide(CEM)
				.setScale(2, RoundingMode.UP);
	}

}
