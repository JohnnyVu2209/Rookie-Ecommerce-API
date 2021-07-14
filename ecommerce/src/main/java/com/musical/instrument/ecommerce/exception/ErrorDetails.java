package com.musical.instrument.ecommerce.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

	private LocalDateTime timestamp;
	private String message;
	private String description;
	
	public ErrorDetails(LocalDateTime now, String message2, String description2) {
		// TODO Auto-generated constructor stub
	}
}
