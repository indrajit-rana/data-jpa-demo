package com.example.datajpademo.controller;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.example.datajpademo.controllers.ProductController;
import com.example.datajpademo.entities.Product;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Mock
	private ProductController productController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetProduct() throws Exception {
		
		when(productController.getProductDetails(Mockito.anyString())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		
		
		ResponseEntity<Product> productDetails = productController.getProductDetails("maggie");
		
		JSONAssert.assertEquals("maggie", productDetails.getBody().getName(), true);
	}
}
