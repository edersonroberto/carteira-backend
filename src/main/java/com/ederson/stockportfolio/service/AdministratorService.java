package com.ederson.stockportfolio.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederson.stockportfolio.dto.AdministratorDto;
import com.ederson.stockportfolio.exceptions.AdministratorAlreadyRegistered;
import com.ederson.stockportfolio.exceptions.StockPortfolioException;
import com.ederson.stockportfolio.factory.AdministratorFactory;
import com.ederson.stockportfolio.model.Administrator;
import com.ederson.stockportfolio.repository.AdministratorRepository;

@Service
public class AdministratorService {
	
	@Autowired
	AdministratorRepository administradoraRepository;
	
	private AdministratorFactory administradoraFactory;
	
	public List<AdministratorDto> list() {
		return administradoraRepository.findAll().stream().map(getAdministradoraFactory()::toAdministradoraDto)
				.collect(Collectors.toList());
	}
	
	public Administrator insert(AdministratorDto administradoraDto) throws StockPortfolioException {
		Administrator administradora2 = administradoraRepository.findByCnpj(administradoraDto.getCnpj());
		if (Objects.nonNull(administradora2)) {
			throw new AdministratorAlreadyRegistered(
					String.format("Administradora com cnpj [%s] já existente", administradoraDto.getCnpj()));
		}

		Administrator administradora = getAdministradoraFactory().toAdminstrator(administradoraDto);
		return administradoraRepository.save(administradora);
	}
	
	private AdministratorFactory getAdministradoraFactory() {
		if (administradoraFactory == null) {
			administradoraFactory = new AdministratorFactory();
		}
		return administradoraFactory;
	}

	public AdministratorDto findById(Long id) {
		Administrator administradora = administradoraRepository.findById(id).orElseThrow();
		return getAdministradoraFactory()
				.toAdministradoraDto(administradora);
	}

	public void delete(String cnpj) {
		Administrator administradora = administradoraRepository.findByCnpj(cnpj);
		administradoraRepository.delete(administradora);
	}

	

}
