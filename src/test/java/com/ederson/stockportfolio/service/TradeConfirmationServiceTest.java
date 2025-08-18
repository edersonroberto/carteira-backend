package com.ederson.stockportfolio.service;

import com.ederson.stockportfolio.dto.*;
import com.ederson.stockportfolio.enums.OperationType;
import com.ederson.stockportfolio.factory.TradeConfirmationFactory;
import com.ederson.stockportfolio.model.TradeConfirmation;
import com.ederson.stockportfolio.repository.TradeConfirmationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TradeConfirmationServiceTest {

    @InjectMocks
    private TradeConfirmationService service;

    @Mock
    private TradeConfirmationRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        TradeConfirmation nota = new TradeConfirmation();
        when(repository.findByOperationType(any(), any(Sort.class))).thenReturn(List.of(nota));
        TradeConfirmationFactory factory = mock(TradeConfirmationFactory.class);
        service = spy(service);
        doReturn(factory).when(service).getNotaFactory();
        when(factory.toListarNotaDto(any())).thenReturn(new ListarNotaDto());
        List<ListarNotaDto> result = service.listar(OperationType.BUY);
        assertEquals(1, result.size());
    }

    @Test
    void testListarNotas() {
        TradeConfirmationFilter filter = new TradeConfirmationFilter();
        filter.setIdCorretora(1L);
        filter.setTipoOperacao(OperationType.BUY);
        TradeConfirmation nota = new TradeConfirmation();
        when(repository.findAll(any(Specification.class))).thenReturn(List.of(nota));
        TradeConfirmationFactory factory = mock(TradeConfirmationFactory.class);
        service = spy(service);
        doReturn(factory).when(service).getNotaFactory();
        when(factory.toListarNotaDto(any())).thenReturn(new ListarNotaDto());
        List<ListarNotaDto> result = service.listarNotas(filter);
        assertEquals(1, result.size());
    }

    @Test
    void testInserirThrowsIfExists() {
        TradeConfirmationDto dto = new TradeConfirmationDto();
        dto.setData(LocalDate.now());
        dto.setValue(BigDecimal.TEN);
        dto.setIdAtivo(1L);
        dto.setIdCorretora(2L);
        
        when(repository.findByDataAndValueAndAssetIdAndBrokerageFirmId(any(), any(), any(), any()))
                .thenReturn(new TradeConfirmation());
        assertThrows(RuntimeException.class, () -> service.inserir(dto));
    }

    @Test
    void testInserirSuccess() {
        TradeConfirmationDto dto = new TradeConfirmationDto();
        dto.setData(LocalDate.now());
        dto.setValue(BigDecimal.TEN);
        dto.setIdAtivo(1L);
        dto.setIdCorretora(2L);
        when(repository.findByDataAndValueAndAssetIdAndBrokerageFirmId(any(), any(), any(), any()))
                .thenReturn(null);
        TradeConfirmationFactory factory = mock(TradeConfirmationFactory.class);
        TradeConfirmation nota = new TradeConfirmation();
        service = spy(service);
        doReturn(factory).when(service).getNotaFactory();
        when(factory.toNota(any())).thenReturn(nota);
        when(repository.save(any())).thenReturn(nota);
        TradeConfirmation result = service.inserir(dto);
        assertNotNull(result);
    }

    @Test
    void testExcluir() {
        service.excluir(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void testDetalhar() {
        TradeConfirmation nota = new TradeConfirmation();
        when(repository.findById(1L)).thenReturn(Optional.of(nota));
        TradeConfirmationFactory factory = mock(TradeConfirmationFactory.class);
        service = spy(service);
        doReturn(factory).when(service).getNotaFactory();
        when(factory.toDetalheNotaDto(any())).thenReturn(new DetalheNotaDto());
        DetalheNotaDto dto = service.detalhar(1L);
        assertNotNull(dto);
    }

    @Test
    void testEditar() {
        TradeConfirmation nota = new TradeConfirmation();
        when(repository.findById(1L)).thenReturn(Optional.of(nota));
        TradeConfirmationFactory factory = mock(TradeConfirmationFactory.class);
        service = spy(service);
        doReturn(factory).when(service).getNotaFactory();
        doNothing().when(factory).toNota(any(), any());
        doNothing().when(repository).flush();
        TradeConfirmationDto dto = new TradeConfirmationDto();
        TradeConfirmation result = service.editar(dto, 1L);
        assertEquals(nota, result);
    }
}
