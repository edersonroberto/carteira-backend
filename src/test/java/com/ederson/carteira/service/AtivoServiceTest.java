package com.ederson.carteira.service;

import org.junit.jupiter.api.Test;

import com.ederson.carteira.dto.AdministradoraDto;
import com.ederson.carteira.dto.AtivoDto;

class AtivoServiceTest {

	@Test
	void testIncluir() {
		AtivoService ativoService = new AtivoService();
		AtivoDto ativoDto = new AtivoDto();
		ativoDto.setNome("Teste");
		ativoDto.setAdministradora(AdministradoraDto.builder().build());
		ativoDto.setCnpj("01861016000151");
		ativoDto.setTicket("CAML3");
		ativoService.incluir(ativoDto);
	}

}
