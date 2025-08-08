package com.ederson.carteira.controller;

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

import com.ederson.carteira.dto.AdministradoraDto;
import com.ederson.carteira.exceptions.CarteiraException;
import com.ederson.carteira.model.Administradora;
import com.ederson.carteira.service.AdministradoraService;

@RestController
@RequestMapping("/administradoras")
public class AdministradoraController {

	@Autowired
	private AdministradoraService administradoraService;

	@GetMapping
	public List<AdministradoraDto> lista() {
		return administradoraService.listar();
	}

	@GetMapping("/{id}")
	public AdministradoraDto buscaPorId(@PathVariable Long id) {
		return administradoraService.buscaPorId(id);
	}

	@PostMapping
	public ResponseEntity<?> incluir(@RequestBody AdministradoraDto administradoraDto) throws CarteiraException {
		Administradora administradora = administradoraService.incluir(administradoraDto);
		return ResponseEntity.created(URI.create("/" + administradora.getId())).build();
	}

	@DeleteMapping(value = "{cnpj}")
	@ResponseBody
	public ResponseEntity<?> excluir(@PathVariable("cnpj") String cnpj) {
		administradoraService.excluir(cnpj);
		return ResponseEntity.ok().build();
	}

}
