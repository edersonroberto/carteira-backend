package com.ederson.stockportfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ederson.stockportfolio.model.Access;
import com.ederson.stockportfolio.service.AcessoService;

@RestController
@RequestMapping("/access")
public class AccessController {
	
	@Autowired
	private AcessoService acessoService;
	
	public List<Access> lista() {
		return acessoService.listar();
	}
	
	public Access incluir(@RequestBody Access acesso) {
		return acessoService.incluir(acesso);
	}

}
