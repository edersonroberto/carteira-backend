package com.ederson.stockportfolio.enums;

public enum AssetType {
	
	STOCK(1, "Stock"),
	REIT(2, "Real Estate Investment Trust");
	
	private Integer id;
	private String descricao;
	
	private AssetType(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
