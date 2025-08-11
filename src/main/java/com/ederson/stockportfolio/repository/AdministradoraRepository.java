package com.ederson.stockportfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ederson.stockportfolio.model.Administrator;

@Repository
public interface AdministradoraRepository extends JpaRepository<Administrator, Long>{

	Administrator findByCnpj(String cnpj);

}
