package com.ederson.carteira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ederson.carteira.model.Corretora;
import com.ederson.carteira.repository.CorretoraRepository;

@RestController
@RequestMapping("/corretoras")
public class CorretoraController {

	@Autowired
	private CorretoraRepository corretoraRepository;
	
	@GetMapping()
	public List<Corretora> lista() {
		return corretoraRepository.findAll();
	}
}
