package com.ederson.stockportfolio.enums;

public enum OperationType {

	BUY("Buy"),
	SALE("Sale"),
	DIVIDEND("Dividend");
	
	private String name;
	
	private OperationType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
