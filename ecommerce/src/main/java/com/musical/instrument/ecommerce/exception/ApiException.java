package com.musical.instrument.ecommerce.exception;

public class ApiException extends RuntimeException{
	public ApiException() {
		
	}
	public ApiException(String message,Throwable cause) {
			super(message,cause);
	}
	public ApiException(String message) {
		super(message);
	}
	public ApiException(Throwable cause) {
		super(cause);
	}
}
