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

import com.ederson.stockportfolio.dto.AssetDto;
import com.ederson.stockportfolio.dto.AssetDetailDto;
import com.ederson.stockportfolio.dto.TicketDto;
import com.ederson.stockportfolio.exceptions.StockPortfolioException;
import com.ederson.stockportfolio.model.Asset;
import com.ederson.stockportfolio.service.AssetService;

@RestController
@RequestMapping("/assets")
public class AssetController {
	
	@Autowired
	private AssetService assetService;
	
	@GetMapping()
	public ResponseEntity<List<Asset>> list() {
		return ResponseEntity.ok(assetService.list());
	}
	
	@GetMapping("/{cnpj}")
	public ResponseEntity<?> detailAsset(@PathVariable String cnpj) {
		AssetDetailDto detalheAtivo = assetService.detailAsset(cnpj);
		return ResponseEntity.ok(detalheAtivo);
	}
	
	@GetMapping("/carteira")
	public ResponseEntity<?> detailStockPortfolio() {
		List<AssetDetailDto> detalheCarteira = assetService.detailStockPortfolio();
		return ResponseEntity.ok(detalheCarteira);
	}
	
	@GetMapping("/tickets")
	public List<TicketDto> listTickets() {
		return assetService.listTickets();
	}
	
	@PostMapping()
	public ResponseEntity<?> incluir(@RequestBody AssetDto ativoDto) throws StockPortfolioException {
		Asset ativo = assetService.incluir(ativoDto);
		return ResponseEntity.created(URI.create("/" + ativo.getId())).build();
	}

}
