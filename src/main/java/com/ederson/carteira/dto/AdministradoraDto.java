package com.ederson.carteira.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdministradoraDto {
	
	private String nome;
	private String razaoSocial;
	private String email;
	private String cnpj;

}
