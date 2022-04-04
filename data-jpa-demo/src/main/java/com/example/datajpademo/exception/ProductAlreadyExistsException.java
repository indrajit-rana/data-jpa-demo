package com.example.datajpademo.exception;

public class ProductAlreadyExistsException extends RuntimeException {

	private String message;
	
	public ProductAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductAlreadyExistsException(String message) {
		this.message = message;
	}
}
