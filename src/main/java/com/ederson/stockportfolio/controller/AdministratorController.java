package com.ederson.stockportfolio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ederson.stockportfolio.dto.AdministratorDto;
import com.ederson.stockportfolio.exceptions.CarteiraException;
import com.ederson.stockportfolio.model.Administrator;
import com.ederson.stockportfolio.service.AdministratorService;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@GetMapping
	public List<AdministratorDto> lista() {
		return administratorService.listar();
	}

	@GetMapping("/{id}")
	public AdministratorDto buscaPorId(@PathVariable Long id) {
		return administratorService.buscaPorId(id);
	}

	@PostMapping
	public ResponseEntity<?> incluir(@RequestBody AdministratorDto administradoraDto) throws CarteiraException {
		Administrator administradora = administratorService.incluir(administradoraDto);
		return ResponseEntity.created(URI.create("/" + administradora.getId())).build();
	}

	@DeleteMapping(value = "{cnpj}")
	@ResponseBody
	public ResponseEntity<?> excluir(@PathVariable("cnpj") String cnpj) {
		administratorService.excluir(cnpj);
		return ResponseEntity.ok().build();
	}

}
