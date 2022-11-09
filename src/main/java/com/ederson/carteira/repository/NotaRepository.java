package com.ederson.carteira.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ederson.carteira.enums.TipoOperacao;
import com.ederson.carteira.model.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long>, JpaSpecificationExecutor<Nota> {

	
	List<Nota> findByAtivoCnpj(String cnpj);
	
	List<Nota> findByCorretoraId(Long idCorretora);
	
	Nota findByDataAndValorAndAtivoIdAndCorretoraId(LocalDate data, BigDecimal valor, Long idAtivo, Long idCorretora);

	List<Nota> findByOperacao(TipoOperacao operacao, Sort by);
}
