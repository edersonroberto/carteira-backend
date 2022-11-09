package com.ederson.carteira.specification;

import org.springframework.data.jpa.domain.Specification;

import com.ederson.carteira.enums.TipoOperacao;
import com.ederson.carteira.model.Ativo;
import com.ederson.carteira.model.Corretora;
import com.ederson.carteira.model.Nota;

public class SpecificationNota {
	
	public static Specification<Nota> ativo (Long id) {
		Ativo ativo = id != null ? new Ativo(id) : null;
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("ativo"), ativo);
	}
	
	public static Specification<Nota> corretora (Long id) {
		Corretora corretora = id != null ? new Corretora(id) : null;
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("corretora"), corretora);
	}
	
	public static Specification<Nota> operacao (TipoOperacao operacao) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("operacao"), operacao);
	}

}
