package com.ederson.stockportfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ederson.stockportfolio.model.BrokerageFirm;
import com.ederson.stockportfolio.repository.BrokerageFirmRepository;

@RestController
@RequestMapping("/corretoras")
public class BrokerageFirmController {

	@Autowired
	private BrokerageFirmRepository brokerageFirmRepository;
	
	@GetMapping()
	public List<BrokerageFirm> lista() {
		return brokerageFirmRepository.findAll();
	}
}
