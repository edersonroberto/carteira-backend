package com.ederson.stockportfolio.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederson.stockportfolio.dto.AssetDto;
import com.ederson.stockportfolio.dto.AssetDetailDto;
import com.ederson.stockportfolio.dto.TicketDto;
import com.ederson.stockportfolio.exceptions.AdministratorNotFoundException;
import com.ederson.stockportfolio.exceptions.StockPortfolioException;
import com.ederson.stockportfolio.factory.AssetFactory;
import com.ederson.stockportfolio.model.Administrator;
import com.ederson.stockportfolio.model.Asset;
import com.ederson.stockportfolio.model.TradeConfirmation;
import com.ederson.stockportfolio.model.TradingSession;
import com.ederson.stockportfolio.repository.AdministratorRepository;
import com.ederson.stockportfolio.repository.AssetRepository;
import com.ederson.stockportfolio.repository.TradeConfirmationRepository;

@Service
public class AssetService {

	@Autowired
	AssetRepository ativoRepository;

	@Autowired
	AdministratorRepository administradoraRepository;

	@Autowired
	TradeConfirmationRepository notaRepository;
	
	@Autowired
	TradingSessionService pregaoService;

	private AssetFactory ativoFactory;

	public List<Asset> list() {
		return ativoRepository.findAll();
	}

	public List<TicketDto> listTickets() {
		List<Asset> list = ativoRepository.findAll();
		return list.stream().map(getAtivoFactory()::toTicketDto).collect(Collectors.toList());
	}

	private AssetFactory getAtivoFactory() {
		if (ativoFactory == null) {
			ativoFactory = new AssetFactory();
		}
		return ativoFactory;
	}

	public Asset incluir(AssetDto ativoDto) throws StockPortfolioException {
		Administrator administrator = administradoraRepository.findByCnpj(ativoDto.getCnpjAdministrator());
		if (Objects.isNull(administrator)) {
			throw new AdministratorNotFoundException(
					String.format("Administradora com cnpj [%s] não encontrada.", ativoDto.getCnpjAdministrator()));
		}

		Asset ativo = getAtivoFactory().toAtivo(ativoDto);
		ativo.setAdministrator(administrator);
		return ativoRepository.save(ativo);
	}

	public AssetDetailDto detailAsset(String cnpj) {
		List<TradeConfirmation> notas = notaRepository.findByAssetCnpj(cnpj);
		if (notas.isEmpty()) {
			return new AssetDetailDto();
		}
		return getAtivoFactory().toDetalheAtivo(notas);
	}

	public List<AssetDetailDto> detailStockPortfolio() {
		List<Asset> ativos = ativoRepository.findAll();
		
		List<AssetDetailDto> carteiraAtivos = new ArrayList<>();
		
		ativos.forEach(ativo -> {
			List<TradeConfirmation> notas = notaRepository.findByAssetCnpj(ativo.getCnpj());
			if (!notas.isEmpty()) {
				AssetDetailDto detalheAtivo = getAtivoFactory().toDetalheAtivo(notas);
				if (detalheAtivo.getAmount() > 0) {	
					TradingSession ultimoPregao = pregaoService.buscaUltimoPregao(ativo.getId());
					detalheAtivo.setValorCota(ultimoPregao != null ? ultimoPregao.getOpeningPrice() : BigDecimal.ZERO);
					carteiraAtivos.add(detalheAtivo);
				}
			}
			
		});
		
		return carteiraAtivos;
	}
}
