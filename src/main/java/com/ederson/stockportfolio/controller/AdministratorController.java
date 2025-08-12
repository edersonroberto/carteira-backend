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
import com.ederson.stockportfolio.exceptions.StockPortfolioException;
import com.ederson.stockportfolio.model.Administrator;
import com.ederson.stockportfolio.service.AdministratorService;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@GetMapping
	public List<AdministratorDto> list() {
		return administratorService.list();
	}

	@GetMapping("/{id}")
	public AdministratorDto buscaPorId(@PathVariable Long id) {
		return administratorService.findById(id);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody AdministratorDto administradoraDto) throws StockPortfolioException {
		Administrator administrator = administratorService.insert(administradoraDto);
		return ResponseEntity.created(URI.create("/" + administrator.getId())).build();
	}

	@DeleteMapping(value = "{cnpj}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("cnpj") String cnpj) {
		administratorService.delete(cnpj);
		return ResponseEntity.ok().build();
	}

}
