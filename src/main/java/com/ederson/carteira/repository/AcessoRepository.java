package com.ederson.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ederson.carteira.model.Acesso;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long>{

}
