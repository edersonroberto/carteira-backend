package com.ederson.stockportfolio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ederson.stockportfolio.model.Access;
import com.ederson.stockportfolio.repository.AccessRepository;

class AccessServiceTest {

	@InjectMocks
	private AccessService accessService;

	@Mock
	private AccessRepository acessoRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testList() {
		Access access = new Access();
		Access access2 = new Access();
		when(acessoRepository.findAll()).thenReturn(List.of(access, access2));
		List<Access> result = accessService.list();
		assertEquals(2, result.size());
	}

	@Test
	void testInsert() {
		Access access = new Access();
		access.setPassword("plain");
		accessService.insert(access);
		verify(acessoRepository).save(access);
	}
}
