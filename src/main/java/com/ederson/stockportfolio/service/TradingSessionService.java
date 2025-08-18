package com.ederson.stockportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederson.stockportfolio.model.TradingSession;
import com.ederson.stockportfolio.repository.TradingSessionRepository;

@Service
public class TradingSessionService {
	
	@Autowired
	TradingSessionRepository pregaoRepository;

	public void insert(TradingSession pregao) {
		pregaoRepository.save(pregao);
	}
	
	public TradingSession buscaUltimoPregao(Long ativoId) {
		return pregaoRepository.findFirstByAssetIdOrderByTradingSessionDateDesc(ativoId);
	}

}
