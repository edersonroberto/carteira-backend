package com.ederson.stockportfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorDto {
	
	private String name;
	private String razaoSocial;
	private String email;
	private String cnpj;

}
