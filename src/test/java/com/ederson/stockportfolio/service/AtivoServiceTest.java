package com.ederson.stockportfolio.service;


import com.ederson.stockportfolio.dto.AssetDto;
import com.ederson.stockportfolio.exceptions.StockPortfolioException;

class AtivoServiceTest {

	//@Test
	void testIncluir() throws StockPortfolioException {
		AssetService ativoService = new AssetService();
		AssetDto ativoDto = new AssetDto();
		ativoDto.setName("Teste");
		ativoDto.setCnpjAdministrator("");
		ativoDto.setCnpj("01861016000151");
		ativoDto.setTicket("CAML3");
		ativoService.incluir(ativoDto);
	}

}
