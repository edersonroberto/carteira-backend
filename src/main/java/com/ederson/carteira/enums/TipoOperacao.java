package com.ederson.carteira.enums;

public enum TipoOperacao {

	COMPRA("COMPRA"),
	VENDA("VENDA"),
	DIVIDENDO("DIVIDENDO");
	
	private String nome;
	
	private TipoOperacao(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
