package com.ederson.carteira.factory;

import com.ederson.carteira.dto.AdministradoraDto;
import com.ederson.carteira.model.Administradora;

public class AdministradoraFactory {
	
	
	public AdministradoraDto toAdministradoraDto(Administradora administradora) {
		return AdministradoraDto.builder()
				.cnpj(administradora.getCnpj())
				.email(administradora.getEmail())
				.nome(administradora.getNome())
				.razaoSocial(administradora.getRazaoSocial())
				.build();
	}

	public Administradora toAdminstradora(AdministradoraDto administradoraDto) {
		return Administradora.builder()
				.cnpj(administradoraDto.getCnpj())
				.email(administradoraDto.getEmail())
				.nome(administradoraDto.getNome())
				.razaoSocial(administradoraDto.getRazaoSocial())
				.build();
	}

}
