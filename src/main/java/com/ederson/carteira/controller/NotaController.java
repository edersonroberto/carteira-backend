package com.ederson.carteira.controller;

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

import com.ederson.carteira.config.PropertiesConfig;
import com.ederson.carteira.dto.DetalheNotaDto;
import com.ederson.carteira.dto.ListarNotaDto;
import com.ederson.carteira.dto.NotaDto;
import com.ederson.carteira.dto.NotaFilter;
import com.ederson.carteira.enums.TipoOperacao;
import com.ederson.carteira.model.Nota;
import com.ederson.carteira.service.NotaService;

@RestController
@RequestMapping("notas")
public class NotaController {

	@Autowired
	private NotaService notaService;
	
	@Autowired
	private PropertiesConfig properties;
	
	@GetMapping
	public List<ListarNotaDto> lista(@RequestParam TipoOperacao operacao) {
		System.out.println("Tipo Operação: " + operacao.getNome());
		System.out.println(properties.getApiCotacao());
		return notaService.listar(operacao);
	}
	
	@PostMapping("/filter")
	public List<ListarNotaDto> filtrar(@RequestBody NotaFilter nota) {
		System.out.println(nota);
		return notaService.listarNotas(nota);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalheNotaDto> detalhar(@PathVariable Long id) {
		DetalheNotaDto nota = notaService.detalhar(id);
		return ResponseEntity.ok(nota);
	}
	
	@PostMapping
	public Nota inserir(@RequestBody NotaDto nota) {
		System.out.println(nota);
		return notaService.inserir(nota);
	}
	
	@PutMapping("/{id}")
	public  Nota editarNota(@RequestBody NotaDto notaDto, @PathVariable Long id) {
	    return notaService.editar(notaDto, id);
	  }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		notaService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
