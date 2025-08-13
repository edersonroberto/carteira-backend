package com.ederson.stockportfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ederson.stockportfolio.model.TradingSession;


@Repository
public interface TradingSessionRepository extends JpaRepository<TradingSession, Long>{
	
	TradingSession findFirstByAtivoIdOrderByDataPregaoDesc(Long ativoId);

}
