package com.ederson.stockportfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ederson.stockportfolio.model.Pregao;


@Repository
public interface PregaoRepository extends JpaRepository<Pregao, Long>{
	
	Pregao findFirstByAtivoIdOrderByDataPregaoDesc(Long ativoId);

}
