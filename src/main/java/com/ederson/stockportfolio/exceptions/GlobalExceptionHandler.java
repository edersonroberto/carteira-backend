package com.ederson.stockportfolio.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ederson.stockportfolio.response.StockPortfolioResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(StockPortfolioException.class)
	public ResponseEntity<StockPortfolioResponse> handleIllegalArgumentException(StockPortfolioException ex) {

		StockPortfolioResponse carteiraResponse = new StockPortfolioResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), ex.getMessage(),
				LocalDateTime.now());

		return new ResponseEntity<>(carteiraResponse, HttpStatus.BAD_REQUEST);
	}
}
