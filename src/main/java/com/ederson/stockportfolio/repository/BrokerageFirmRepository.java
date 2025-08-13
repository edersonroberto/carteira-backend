package com.ederson.stockportfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ederson.stockportfolio.model.BrokerageFirm;

@Repository
public interface BrokerageFirmRepository extends JpaRepository<BrokerageFirm, Long>{

}
