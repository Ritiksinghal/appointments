package com.eastvantage.appointment.exception;

public class NotFoundException extends RuntimeException{

	private final String errorCode;
	private final String errorMessage;
	
	public String getErrorCode() {
		return errorCode;
	}
	
	@Override
	public String getMessage() {
		return errorMessage;
	}
	
	public NotFoundException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
