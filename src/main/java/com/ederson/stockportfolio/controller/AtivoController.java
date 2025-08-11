package com.ederson.stockportfolio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ederson.stockportfolio.dto.AtivoDto;
import com.ederson.stockportfolio.dto.DetalheAtivoDto;
import com.ederson.stockportfolio.dto.TicketDto;
import com.ederson.stockportfolio.exceptions.CarteiraException;
import com.ederson.stockportfolio.model.Asset;
import com.ederson.stockportfolio.service.AtivoService;

@RestController
@RequestMapping("/ativos")
public class AtivoController {
	
	@Autowired
	private AtivoService ativoService;
	
	@GetMapping()
	public ResponseEntity<List<Asset>> lista() {
		return ResponseEntity.ok(ativoService.listar());
	}
	
	@GetMapping("/{cnpj}")
	public ResponseEntity<?> detalharAtivo(@PathVariable String cnpj) {
		DetalheAtivoDto detalheAtivo = ativoService.detalharAtivo(cnpj);
		return ResponseEntity.ok(detalheAtivo);
	}
	
	@GetMapping("/carteira")
	public ResponseEntity<?> detalharCarteira() {
		List<DetalheAtivoDto> detalheCarteira = ativoService.detalharCarteira();
		return ResponseEntity.ok(detalheCarteira);
	}
	
	@GetMapping("/tickets")
	public List<TicketDto> listaTickets() {
		return ativoService.listarTickets();
	}
	
	@PostMapping()
	public ResponseEntity<?> incluir(@RequestBody AtivoDto ativoDto) throws CarteiraException {
		Asset ativo = ativoService.incluir(ativoDto);
		return ResponseEntity.created(URI.create("/" + ativo.getId())).build();
	}

}
