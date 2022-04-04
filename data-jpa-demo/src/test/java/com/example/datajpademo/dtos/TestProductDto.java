package com.example.datajpademo.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.example.datajpademo.entities.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class TestProductDto {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	String json  = "{\"id\":null,\"name\":\"ABC\",\"price\":10}";

	@Test 
	public void testSerialization() throws JsonProcessingException {
		Product product = Product.builder().name("ABC").price(new BigDecimal(10)).build();
		String writeValueAsString = mapper.writeValueAsString(product);
		
		assertEquals(json, writeValueAsString);
	}
	
	@Test 
	public void testDeserialization() throws JsonMappingException, JsonProcessingException {
		Product product = mapper.readValue(json, Product.class);
		assertEquals("ABC", product.getName());
	}
}