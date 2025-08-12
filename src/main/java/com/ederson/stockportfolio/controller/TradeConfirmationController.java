package com.ederson.stockportfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ederson.stockportfolio.config.PropertiesConfig;
import com.ederson.stockportfolio.dto.DetalheNotaDto;
import com.ederson.stockportfolio.dto.ListarNotaDto;
import com.ederson.stockportfolio.dto.TradeConfirmationDto;
import com.ederson.stockportfolio.dto.TradeConfirmationFilter;
import com.ederson.stockportfolio.enums.OperationType;
import com.ederson.stockportfolio.model.TradeConfirmation;
import com.ederson.stockportfolio.service.TradeConfirmationService;

@RestController
@RequestMapping("notas")
public class TradeConfirmationController {

	@Autowired
	private TradeConfirmationService tradeConfirmationService;
	
	@Autowired
	private PropertiesConfig properties;
	
	@GetMapping
	public List<ListarNotaDto> lista(@RequestParam OperationType operationType) {
		System.out.println("Tipo Operação: " + operationType.getName());
		System.out.println(properties.getApiCotacao());
		return tradeConfirmationService.listar(operationType);
	}
	
	@PostMapping("/filter")
	public List<ListarNotaDto> filtrar(@RequestBody TradeConfirmationFilter nota) {
		System.out.println(nota);
		return tradeConfirmationService.listarNotas(nota);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalheNotaDto> detalhar(@PathVariable Long id) {
		DetalheNotaDto nota = tradeConfirmationService.detalhar(id);
		return ResponseEntity.ok(nota);
	}
	
	@PostMapping
	public TradeConfirmation inserir(@RequestBody TradeConfirmationDto nota) {
		System.out.println(nota);
		return tradeConfirmationService.inserir(nota);
	}
	
	@PutMapping("/{id}")
	public  TradeConfirmation editarNota(@RequestBody TradeConfirmationDto notaDto, @PathVariable Long id) {
	    return tradeConfirmationService.editar(notaDto, id);
	  }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		tradeConfirmationService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
