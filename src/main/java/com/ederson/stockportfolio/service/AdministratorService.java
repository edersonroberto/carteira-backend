package com.ederson.stockportfolio.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederson.stockportfolio.dto.AdministratorDto;
import com.ederson.stockportfolio.exceptions.AdministradoraJaCadastradaException;
import com.ederson.stockportfolio.exceptions.CarteiraException;
import com.ederson.stockportfolio.factory.AdministradoraFactory;
import com.ederson.stockportfolio.model.Administrator;
import com.ederson.stockportfolio.repository.AdministradoraRepository;

@Service
public class AdministratorService {
	
	@Autowired
	AdministradoraRepository administradoraRepository;
	
	private AdministradoraFactory administradoraFactory;
	
	public List<AdministratorDto> listar() {
		return administradoraRepository.findAll().stream().map(getAdministradoraFactory()::toAdministradoraDto)
				.collect(Collectors.toList());
	}
	
	public Administrator incluir(AdministratorDto administradoraDto) throws CarteiraException {
		Administrator administradora2 = administradoraRepository.findByCnpj(administradoraDto.getCnpj());
		if (Objects.nonNull(administradora2)) {
			throw new AdministradoraJaCadastradaException(
					String.format("Administradora com cnpj [%s] já existente", administradoraDto.getCnpj()));
		}

		Administrator administradora = getAdministradoraFactory().toAdminstradora(administradoraDto);
		return administradoraRepository.save(administradora);
	}
	
	private AdministradoraFactory getAdministradoraFactory() {
		if (administradoraFactory == null) {
			administradoraFactory = new AdministradoraFactory();
		}
		return administradoraFactory;
	}

	public AdministratorDto buscaPorId(Long id) {
		Administrator administradora = administradoraRepository.findById(id).orElseThrow();
		return getAdministradoraFactory()
				.toAdministradoraDto(administradora);
	}

	public void excluir(String cnpj) {
		Administrator administradora = administradoraRepository.findByCnpj(cnpj);
		administradoraRepository.delete(administradora);
	}

	

}
