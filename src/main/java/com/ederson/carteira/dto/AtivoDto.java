package com.ederson.carteira.dto;

import lombok.Data;

@Data
public class AtivoDto {
	
	private AdministradoraDto administradora;
	private String nome;
	private String ticket;
	private String cnpj;

}
