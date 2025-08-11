package com.ederson.stockportfolio.enums;

public enum OperationType {

	COMPRA("COMPRA"),
	VENDA("VENDA"),
	DIVIDENDO("DIVIDENDO");
	
	private String nome;
	
	private OperationType(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
