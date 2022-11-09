package com.ederson.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ederson.carteira.model.Corretora;

@Repository
public interface CorretoraRepository extends JpaRepository<Corretora, Long>{

}
