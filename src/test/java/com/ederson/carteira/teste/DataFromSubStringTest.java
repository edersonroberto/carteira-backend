package com.ederson.carteira.teste;

import org.junit.jupiter.api.Test;

public class DataFromSubStringTest {
	
	@Test
	void testData() {
		String stringData = "20220617";
		System.out.println(stringData.substring(0, 4));
		System.out.println(stringData.substring(4, 6));
		System.out.println(stringData.substring(6, 8));
	}

}
