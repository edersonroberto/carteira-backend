package com.ederson.stockportfolio.factory;

import com.ederson.stockportfolio.dto.AdministratorDto;
import com.ederson.stockportfolio.model.Administrator;

public class AdministratorFactory {
	
	
	public AdministratorDto toAdministradoraDto(Administrator administrator) {
		return AdministratorDto.builder()
				.cnpj(administrator.getCnpj())
				.email(administrator.getEmail())
				.name(administrator.getName())
				.razaoSocial(administrator.getRazaoSocial())
				.build();
	}

	public Administrator toAdminstrator(AdministratorDto administratorDto) {
		return Administrator.builder()
				.cnpj(administratorDto.getCnpj())
				.email(administratorDto.getEmail())
				.name(administratorDto.getName())
				.razaoSocial(administratorDto.getRazaoSocial())
				.build();
	}

}
