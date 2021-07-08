package com.musical.instrument.ecommerce.exception;

public class Exception extends RuntimeException{
	public Exception() {
		
	}
	public Exception(String message,Throwable cause) {
			super(message,cause);
	}
	public Exception(String message) {
		super(message);
	}
	public Exception(Throwable cause) {
		super(cause);
	}
}
