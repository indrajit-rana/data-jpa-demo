package com.example.datajpademo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.datajpademo.dtos.ProductDto;
import com.example.datajpademo.entities.Product;
import com.example.datajpademo.services.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping("/store")
	public ResponseEntity<Product> saveProduct(@RequestBody ProductDto productDto) {
		log.info("saveProduct method of ProductController class is invoked with Product details {}",productDto);
		return new ResponseEntity<Product>( productService.saveProduct(productDto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> fetchProductsDetails(){
		log.info("fetchProductsDetails method of ProductController class is invoked.");
		return new ResponseEntity<List<Product>>(productService.fetchProductsDetails(), HttpStatus.OK);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Product> getProductDetails(@PathVariable String name) {
		log.info("getProductDetails method of ProductController class is invoked with Product name {}",name);
		return new ResponseEntity<Product>(productService.getProductDetails(name), HttpStatus.OK);
	}
	
	@DeleteMapping("/name/{name}")
	public ResponseEntity<String> deleteProductDetails(@PathVariable String name) {
		log.info("deleteProductDetails method of ProductController class is invoked with Product name {}",name);
		return new ResponseEntity<String>(productService.deleteProductDetails(name),HttpStatus.OK);
	}
}
