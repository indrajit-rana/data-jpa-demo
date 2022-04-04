package com.example.datajpademo.exception;

public class ProductNotFoundException extends RuntimeException {

	private String message;
	
	public ProductNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductNotFoundException(String message) {
		this.message = message;
	}
}
