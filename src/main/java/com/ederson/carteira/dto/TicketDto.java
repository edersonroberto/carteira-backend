package com.ederson.carteira.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketDto {

	private Long id;
	private String nome;
}
