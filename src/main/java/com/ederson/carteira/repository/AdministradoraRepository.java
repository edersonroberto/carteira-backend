package com.ederson.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ederson.carteira.model.Administradora;

@Repository
public interface AdministradoraRepository extends JpaRepository<Administradora, Long>{

	Administradora findByCnpj(String cnpj);

}
