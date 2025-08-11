package com.ederson.stockportfolio.factory;

import com.ederson.stockportfolio.dto.AdministratorDto;
import com.ederson.stockportfolio.model.Administrator;

public class AdministradoraFactory {
	
	
	public AdministratorDto toAdministradoraDto(Administrator administradora) {
		return AdministratorDto.builder()
				.cnpj(administradora.getCnpj())
				.email(administradora.getEmail())
				.nome(administradora.getNome())
				.razaoSocial(administradora.getRazaoSocial())
				.build();
	}

	public Administrator toAdminstradora(AdministratorDto administradoraDto) {
		return Administrator.builder()
				.cnpj(administradoraDto.getCnpj())
				.email(administradoraDto.getEmail())
				.nome(administradoraDto.getNome())
				.razaoSocial(administradoraDto.getRazaoSocial())
				.build();
	}

}
