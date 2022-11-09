package com.ederson.carteira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederson.carteira.model.Pregao;
import com.ederson.carteira.repository.PregaoRepository;

@Service
public class PregaoService {
	
	@Autowired
	PregaoRepository pregaoRepository;

	public void inserir(Pregao pregao) {
		pregaoRepository.save(pregao);
	}
	
	public Pregao buscaUltimoPregao(Long ativoId) {
		return pregaoRepository.findFirstByAtivoIdOrderByDataPregaoDesc(ativoId);
	}

}
