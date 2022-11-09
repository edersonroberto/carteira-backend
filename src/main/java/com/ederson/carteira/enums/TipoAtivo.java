package com.ederson.carteira.enums;

public enum TipoAtivo {
	
	ACAO(1, "Ação"),
	FII(2, "Fundo Imobiliario");
	
	private Integer id;
	private String descricao;
	
	private TipoAtivo(Integer id, String descricao) {
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
