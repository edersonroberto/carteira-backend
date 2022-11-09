package com.ederson.carteira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ederson.carteira.model.Acesso;
import com.ederson.carteira.service.AcessoService;

@RestController
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	@GetMapping("/acessos")
	public List<Acesso> lista() {
		return acessoService.listar();
	}
	
	@PostMapping("/acessos")
	public Acesso incluir(@RequestBody Acesso acesso) {
		return acessoService.incluir(acesso);
	}
	
	

}
