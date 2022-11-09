package com.ederson.carteira.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederson.carteira.dto.AdministradoraDto;
import com.ederson.carteira.factory.AdministradoraFactory;
import com.ederson.carteira.model.Administradora;
import com.ederson.carteira.repository.AdministradoraRepository;

@Service
public class AdministradoraService {
	
	@Autowired
	AdministradoraRepository administradoraRepository;
	
	private AdministradoraFactory administradoraFactory;
	
	public List<AdministradoraDto> listar() {
		return administradoraRepository.findAll().stream().map(getAdministradoraFactory()::toAdministradoraDto)
				.collect(Collectors.toList());
	}
	
	public Administradora incluir(AdministradoraDto administradoraDto) {
		Administradora administradora = getAdministradoraFactory().toAdminstradora(administradoraDto);
		return administradoraRepository.save(administradora);
	}
	
	private AdministradoraFactory getAdministradoraFactory() {
		if (administradoraFactory == null) {
			administradoraFactory = new AdministradoraFactory();
		}
		return administradoraFactory;
	}

	public AdministradoraDto buscaPorId(Long id) {
		Administradora administradora = administradoraRepository.findById(id).orElseThrow();
		return getAdministradoraFactory()
				.toAdministradoraDto(administradora);
	}

	public void excluir(String cnpj) {
		Administradora administradora = administradoraRepository.findByCnpj(cnpj);
		administradoraRepository.delete(administradora);
	}

	

}
