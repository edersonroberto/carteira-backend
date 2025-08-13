package com.ederson.stockportfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ederson.stockportfolio.model.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

}
