package com.example.datajpademo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.datajpademo.exception.ProductAlreadyExistsException;
import com.example.datajpademo.exception.ProductNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity productNotFoundExceptionHandler(ProductNotFoundException productNotFoundException) {
		return new ResponseEntity("Not Found",HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity productAlreadyExistsExceptionHandler(ProductAlreadyExistsException productAlreadyExistsException) {
		return new ResponseEntity("Already Exists", HttpStatus.BAD_REQUEST);
	}
}
