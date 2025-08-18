package com.ederson.stockportfolio.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ederson.stockportfolio.enums.OperationType;
import com.ederson.stockportfolio.model.TradeConfirmation;

@Repository
public interface TradeConfirmationRepository extends JpaRepository<TradeConfirmation, Long>, JpaSpecificationExecutor<TradeConfirmation> {

	
	List<TradeConfirmation> findByAssetCnpj(String cnpj);
	
	List<TradeConfirmation> findByBrokerageFirmId(Long idCorretora);
	
	TradeConfirmation findByDataAndValueAndAssetIdAndBrokerageFirmId(LocalDate data, BigDecimal valor, Long idAtivo, Long idCorretora);

	List<TradeConfirmation> findByOperationType(OperationType operationType, Sort by);
}
