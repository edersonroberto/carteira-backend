package com.ederson.stockportfolio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdministratorDto {
	
	private String name;
	private String razaoSocial;
	private String email;
	private String cnpj;

}
