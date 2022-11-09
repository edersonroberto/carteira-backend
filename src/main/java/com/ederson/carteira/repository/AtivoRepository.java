package com.ederson.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ederson.carteira.model.Ativo;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {

}
