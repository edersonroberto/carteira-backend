package com.ederson.stockportfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ederson.stockportfolio.model.Access;

@Repository
public interface AcessoRepository extends JpaRepository<Access, Long>{

}
