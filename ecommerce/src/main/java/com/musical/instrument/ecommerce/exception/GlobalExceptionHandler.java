package com.musical.instrument.ecommerce.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.musical.instrument.ecommerce.dto.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<?> dataNotFoundException(DataNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CreateDataFailException.class)
	public ResponseEntity<?> createDataFailException(CreateDataFailException ex, WebRequest request) {
		ResponseDTO responseDTO = new ResponseDTO();
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		responseDTO.setErrorCode(ex.getMessage());
		responseDTO.setData(errorDetails);
		return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UpdateDataFailException.class)
	public ResponseEntity<?> updateDataFailException(UpdateDataFailException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DeleteDataFailException.class)
	public ResponseEntity<?> deleteDataFailException(DeleteDataFailException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserNameExistsException.class)
	public ResponseEntity<?> userNameExistsException(UserNameExistsException ex, WebRequest request) {
		ResponseDTO responseDTO = new ResponseDTO();
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		responseDTO.setErrorCode(ex.getMessage());
		responseDTO.setData(errorDetails);
		return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(EmailExistsException.class)
	public ResponseEntity<?> emailExistsException(EmailExistsException ex, WebRequest request) {
		ResponseDTO responseDTO = new ResponseDTO();
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		responseDTO.setErrorCode(ex.getMessage());
		responseDTO.setData(errorDetails);
		return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(LoadDataFailException.class)
	public ResponseEntity<?> loadDataFailException(LoadDataFailException ex, WebRequest request) {
		ResponseDTO responseDTO = new ResponseDTO();
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		responseDTO.setErrorCode(ex.getMessage());
		responseDTO.setData(errorDetails);
		return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
