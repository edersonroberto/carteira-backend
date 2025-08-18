package com.ederson.stockportfolio.service;

import com.ederson.stockportfolio.dto.AdministratorDto;
import com.ederson.stockportfolio.exceptions.AdministratorAlreadyRegistered;
import com.ederson.stockportfolio.model.Administrator;
import com.ederson.stockportfolio.repository.AdministratorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdministratorServiceTest {

    @InjectMocks
    private AdministratorService administratorService;

    @Mock
    private AdministratorRepository administratorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        Administrator admin = new Administrator();
        when(administratorRepository.findAll()).thenReturn(List.of(admin));
        List<AdministratorDto> result = administratorService.list();
        assertEquals(1, result.size());
    }

    @Test
    void testInsertThrowsAlreadyRegistered() {
        AdministratorDto dto = new AdministratorDto("name", "razaoSocial", "eamil", "123");
        when(administratorRepository.findByCnpj("123")).thenReturn(new Administrator());
        assertThrows(AdministratorAlreadyRegistered.class, () -> administratorService.insert(dto));
    }

    @Test
    void testInsertSuccess() throws Exception {
        AdministratorDto dto = new AdministratorDto();
        dto.setCnpj("123");
        when(administratorRepository.findByCnpj("123")).thenReturn(null);
        when(administratorRepository.save(any())).thenReturn(new Administrator());
        Administrator result = administratorService.insert(dto);
        assertNotNull(result);
    }

    @Test
    void testFindById() {
        Administrator admin = new Administrator();
        when(administratorRepository.findById(1L)).thenReturn(Optional.of(admin));
        AdministratorDto dto = administratorService.findById(1L);
        assertNotNull(dto);
    }

    @Test
    void testDelete() {
        Administrator admin = new Administrator();
        when(administratorRepository.findByCnpj("123")).thenReturn(admin);
        administratorService.delete("123");
        verify(administratorRepository).delete(admin);
    }
}
