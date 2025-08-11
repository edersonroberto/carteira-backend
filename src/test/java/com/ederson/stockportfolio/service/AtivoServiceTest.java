package com.ederson.stockportfolio.service;

import org.junit.jupiter.api.Test;

import com.ederson.stockportfolio.dto.AtivoDto;
import com.ederson.stockportfolio.exceptions.CarteiraException;

class AtivoServiceTest {

	//@Test
	void testIncluir() throws CarteiraException {
		AtivoService ativoService = new AtivoService();
		AtivoDto ativoDto = new AtivoDto();
		ativoDto.setNome("Teste");
		ativoDto.setCnpjAdministrator("");
		ativoDto.setCnpj("01861016000151");
		ativoDto.setTicket("CAML3");
		ativoService.incluir(ativoDto);
	}

}
