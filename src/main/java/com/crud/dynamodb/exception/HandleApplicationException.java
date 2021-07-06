package com.crud.dynamodb.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleApplicationException {
	
	@ExceptionHandler
	public ResponseEntity<StandardError> handleStandarError(Exception e, HttpServletRequest request) {
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setError("Internal Server Error");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
	
	@ExceptionHandler(EntityNotFoundExeceptions.class)
	public ResponseEntity<StandardError> handleNotFoundError(EntityNotFoundExeceptions e, HttpServletRequest request) {
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Resource not found");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> handleValidationExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError err = new ValidationError();
		
		Map<String, String> errors = new HashMap<>();
	    e.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    
	    err.setTimestamp(Instant.now());
	    err.setStatus(HttpStatus.BAD_REQUEST.value());
	    err.setError(errors);
	    err.setMessage("Body malformed");
	    err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
