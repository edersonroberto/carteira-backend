package com.ederson.stockportfolio.enums;

public enum OperationType {

	COMPRA("COMPRA"),
	VENDA("VENDA"),
	DIVIDENDO("DIVIDENDO");
	
	private String name;
	
	private OperationType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
