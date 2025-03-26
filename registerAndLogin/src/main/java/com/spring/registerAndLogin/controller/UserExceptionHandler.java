package com.spring.registerAndLogin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.registerAndLogin.exception.NoLoggedInUserException;
import com.spring.registerAndLogin.exception.ProductNotFoundException;

@RestControllerAdvice
public class UserExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(NoLoggedInUserException.class)
	public ResponseEntity<String> noLoggedInUserException(NoLoggedInUserException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> productNotFoundException(ProductNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
	}
}
