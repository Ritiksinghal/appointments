package com.eastvantage.appointment.exception;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppointmentException {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentException.class);
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException) {
		return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler({Exception.class})
	protected ResponseEntity<String> handleException(Exception e, Locale locale) {
		LOGGER.error("Exception occured", e);
		return ResponseEntity.badRequest().body("Exception Occured " + e);
	}
}
