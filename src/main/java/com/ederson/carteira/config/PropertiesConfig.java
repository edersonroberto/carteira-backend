package com.ederson.carteira.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {

	//@Value("app.emolumento_2019")
	//private BigDecimal emolumento_2019;
	
	@Value("${app.api_cotacao}")
	private String apiCotacao;
	
	public String getApiCotacao() {
		return apiCotacao;
	}
}
