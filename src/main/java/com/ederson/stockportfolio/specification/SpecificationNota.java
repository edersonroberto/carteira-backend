package com.ederson.stockportfolio.specification;

import org.springframework.data.jpa.domain.Specification;

import com.ederson.stockportfolio.enums.OperationType;
import com.ederson.stockportfolio.model.Asset;
import com.ederson.stockportfolio.model.BrokerageFirm;
import com.ederson.stockportfolio.model.Nota;

public class SpecificationNota {
	
	public static Specification<Nota> ativo (Long id) {
		Asset ativo = id != null ? new Asset(id) : null;
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("ativo"), ativo);
	}
	
	public static Specification<Nota> corretora (Long id) {
		BrokerageFirm corretora = id != null ? new BrokerageFirm(id) : null;
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("corretora"), corretora);
	}
	
	public static Specification<Nota> operacao (OperationType operacao) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("operacao"), operacao);
	}

}
