package com.ederson.carteira.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ederson.carteira.response.CarteiraResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CarteiraException.class)
	public ResponseEntity<CarteiraResponse> handleIllegalArgumentException(CarteiraException ex) {

		CarteiraResponse carteiraResponse = new CarteiraResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), ex.getMessage(),
				LocalDateTime.now());

		return new ResponseEntity<>(carteiraResponse, HttpStatus.BAD_REQUEST);
	}
}
