package com.ederson.stockportfolio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ederson.stockportfolio.dto.AssetDetailDto;
import com.ederson.stockportfolio.dto.AssetDto;
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

class AssetServiceTest {

	@InjectMocks
    private AssetService assetService;

    @Mock
    private AssetRepository assetRepository;
    @Mock
    private AdministratorRepository administratorRepository;
    @Mock
    private TradeConfirmationRepository tradeConfirmationRepository;
    @Mock
    private TradingSessionService tradingSessionService;
    
    Asset asset = new Asset();
    String cnpj = "59281253000123";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
		asset = Asset.builder().cnpj(cnpj).name("Test Asset").ticket("TEST3").build();
    }
	
	@Test
    void list_ShouldReturnAListOfAssets() {
        List<Asset> assets = Arrays.asList(new Asset(), new Asset());
        when(assetRepository.findAll()).thenReturn(assets);
        List<Asset> result = assetService.list();
        assertEquals(2, result.size());
    }

    @Test
    void listTickets() {

    	AssetFactory factory = spy(new AssetFactory());
    	assetService = spy(assetService);

        when(assetRepository.findAll()).thenReturn(List.of(asset));
        doReturn(factory).when(assetService).getAtivoFactory();
        
        List<TicketDto> tickets = assetService.listTickets();
        assertNotNull(tickets);
    }

    @Test
    void insert_ShouldThrowsAdministratorNotFound_WhenAdministratorNotExists() {
        AssetDto dto = new AssetDto();
        dto.setCnpjAdministrator(cnpj);
        when(administratorRepository.findByCnpj(cnpj)).thenReturn(null);
        assertThrows(AdministratorNotFoundException.class, () -> assetService.insert(dto));
    }

    @Test
    void insert_ShouldInsertAnAssetWithSuccess() throws StockPortfolioException {
        AssetDto dto = new AssetDto();
        dto.setCnpjAdministrator("123");
        Administrator admin = new Administrator();
        when(administratorRepository.findByCnpj("123")).thenReturn(admin);

        AssetFactory factory = spy(new AssetFactory());
        assetService = spy(assetService);
        doReturn(factory).when(assetService).getAtivoFactory();
        doReturn(asset).when(factory).toAtivo(dto);
        when(assetRepository.save(any())).thenReturn(asset);
        
        Asset result = assetService.insert(dto);
        assertNotNull(result);
        assertEquals(cnpj, result.getCnpj());
        assertEquals("Test Asset", result.getName());
    }

    @Test
    void detailAsset_ShoulReturnAnEmptyDetail_WhenNotExistsAnyTradeConfirmation() {
        when(tradeConfirmationRepository.findByAssetCnpj("123")).thenReturn(Collections.emptyList());
        AssetDetailDto dto = assetService.detailAsset("123");
        assertNotNull(dto);
        assertNull(dto.getNotas());
    }

    @Test
    void detailAsset_ShouldDetailAnAssetWithData() {
        TradeConfirmation tc = new TradeConfirmation();
        when(tradeConfirmationRepository.findByAssetCnpj("123")).thenReturn(List.of(tc));
        AssetFactory factory = spy(new AssetFactory());
        assetService = spy(assetService);
        doReturn(factory).when(assetService).getAtivoFactory();
        doReturn(new AssetDetailDto()).when(factory).toDetalheAtivo(anyList());
        AssetDetailDto dto = assetService.detailAsset("123");
        assertNotNull(dto);
    }

    @Test
    void detailStockPortfolio_ShouldReturnAdetailedSotckPortfolio() {

        asset.setId(1L);
        
        TradeConfirmation tc = new TradeConfirmation();
        AssetFactory factory = spy(new AssetFactory());
        TradingSession session = new TradingSession();
        AssetDetailDto detail = new AssetDetailDto();
        detail.setAmount(1L);
        
        when(assetRepository.findAll()).thenReturn(List.of(asset));
        when(tradeConfirmationRepository.findByAssetCnpj(cnpj)).thenReturn(List.of(tc));
        assetService = spy(assetService);
        doReturn(factory).when(assetService).getAtivoFactory();
        doReturn(detail).when(factory).toDetalheAtivo(anyList());
        session.setOpeningPrice(BigDecimal.TEN);
        when(tradingSessionService.buscaUltimoPregao(1L)).thenReturn(session);
        List<AssetDetailDto> result = assetService.detailStockPortfolio();
        
        assertEquals(1, result.size());
        assertEquals(BigDecimal.TEN, result.get(0).getValorCota());
    }
    
    @Test
    void detailStockPortfolio_ShouldReturnAdetailedSotckPortfolioWithAtLeastOneAssetWithoutTradedConfirmation() {

        asset.setId(1L);
        Asset asset2 = new Asset();
        asset2.setCnpj("00000000000191");
        
        Asset asset3 = new Asset();
        asset3.setCnpj("00000000000272");
        
        TradeConfirmation tcForAsset1 = new TradeConfirmation();
        AssetFactory factory = spy(new AssetFactory());
        TradingSession session = new TradingSession();
        AssetDetailDto detail = new AssetDetailDto();
        detail.setAmount(1L);
        
        TradeConfirmation tcForAsset3 = new TradeConfirmation();
        AssetDetailDto detailForAsset3 = new AssetDetailDto();
        detailForAsset3.setAmount(0L);
        
        when(assetRepository.findAll()).thenReturn(List.of(asset, asset2, asset3));
        when(tradeConfirmationRepository.findByAssetCnpj(cnpj)).thenReturn(List.of(tcForAsset1));
        when(tradeConfirmationRepository.findByAssetCnpj(asset3.getCnpj())).thenReturn(List.of(tcForAsset3));
        when(tradeConfirmationRepository.findByAssetCnpj(asset2.getCnpj())).thenReturn(Collections.emptyList());
        
        assetService = spy(assetService);
        doReturn(factory).when(assetService).getAtivoFactory();
        doReturn(detail).when(factory).toDetalheAtivo(List.of(tcForAsset1));
        doReturn(detailForAsset3).when(factory).toDetalheAtivo(List.of(tcForAsset3));
        
        session.setOpeningPrice(BigDecimal.TEN);
        when(tradingSessionService.buscaUltimoPregao(1L)).thenReturn(session);
        List<AssetDetailDto> result = assetService.detailStockPortfolio();
        
        assertEquals(1, result.size());
        assertEquals(BigDecimal.TEN, result.get(0).getValorCota());
    }
    
    @Test
    void detailStockPortfolio_ShouldReturnAdetailedSotckPortfolioWith_WhenThereIsNotTradedSession() {

        asset.setId(1L);
        
        TradeConfirmation tcForAsset1 = new TradeConfirmation();
        AssetFactory factory = spy(new AssetFactory());
        AssetDetailDto detail = new AssetDetailDto();
        detail.setAmount(1L);
        
        TradeConfirmation tcForAsset3 = new TradeConfirmation();
        AssetDetailDto detailForAsset3 = new AssetDetailDto();
        detailForAsset3.setAmount(0L);
        
        when(assetRepository.findAll()).thenReturn(List.of(asset));
        when(tradeConfirmationRepository.findByAssetCnpj(cnpj)).thenReturn(List.of(tcForAsset1));
        
        assetService = spy(assetService);
        doReturn(factory).when(assetService).getAtivoFactory();
        doReturn(detail).when(factory).toDetalheAtivo(List.of(tcForAsset1));
        doReturn(detailForAsset3).when(factory).toDetalheAtivo(List.of(tcForAsset3));
        
        when(tradingSessionService.buscaUltimoPregao(1L)).thenReturn(null);
        List<AssetDetailDto> result = assetService.detailStockPortfolio();
        
        assertEquals(1, result.size());
        assertEquals(BigDecimal.ZERO, result.get(0).getValorCota());
    }
    
    

}
