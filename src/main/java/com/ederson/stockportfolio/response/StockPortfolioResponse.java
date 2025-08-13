package com.ederson.stockportfolio.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StockPortfolioResponse {
	
	private int code;
	private String error;
	private String message;
	private LocalDateTime localDateServer;
	

}
