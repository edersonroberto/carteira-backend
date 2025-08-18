package com.ederson.stockportfolio.service;

import com.ederson.stockportfolio.model.TradingSession;
import com.ederson.stockportfolio.repository.TradingSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TradingSessionServiceTest {

    @InjectMocks
    private TradingSessionService service;

    @Mock
    private TradingSessionRepository pregaoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        TradingSession pregao = new TradingSession();
        service.insert(pregao);
        verify(pregaoRepository).save(pregao);
    }

    @Test
    void testBuscaUltimoPregao() {
        TradingSession pregao = new TradingSession();
        when(pregaoRepository.findFirstByAssetIdOrderByTradingSessionDateDesc(1L)).thenReturn(pregao);
        TradingSession result = service.buscaUltimoPregao(1L);
        assertEquals(pregao, result);
    }
}
