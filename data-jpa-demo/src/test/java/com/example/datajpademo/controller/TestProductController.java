package com.example.datajpademo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.datajpademo.controllers.ProductController;
import com.example.datajpademo.dtos.ProductDto;
import com.example.datajpademo.entities.Product;
import com.example.datajpademo.services.ProductService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductController.class)
class TestProductController {
	
	@InjectMocks
	private ProductController productController;

	@MockBean
	private ProductService productService;

	@Autowired
	private MockMvc mockMvc;

	String jsonContent = "{\"name\": \"ABC\",\"price\": 100}";

	@Test
	public void testSaveProduct() throws Exception {

		Product product = Product.builder().name("ABC").price(new BigDecimal(100)).build();
		
		String expected = "{\"name\": \"ABC\",\"price\": 100}";

		Mockito.when(productService.saveProduct(Mockito.any(ProductDto.class))).thenReturn(product);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/products/store")
				.accept(MediaType.APPLICATION_JSON).content(jsonContent).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

//		ProductDto productDto = ProductDto.builder().name("ABC").price(new BigDecimal(100)).build();

//		Product saveProduct = productService.saveProduct(productDto);
//		assertEquals(productDto.getName(), saveProduct.getName());

//		verify(productService).saveProduct(productDto);
	}

	@Test
	public void testFetchProductsDetails() throws Exception {

		List<Product> products = List.of(Product.builder().name("A").price(new BigDecimal(10)).build(),
				Product.builder().name("B").price(new BigDecimal(11)).build(),
				Product.builder().name("C").price(new BigDecimal(12)).build());

		Mockito.when(productService.fetchProductsDetails()).thenReturn(products);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/products")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	public void testGetProductDetails() throws Exception {

		Product product = Product.builder().name("A").price(new BigDecimal(10)).build();
		String expected = "{\"name\": \"A\",\"price\": 10}";

		Mockito.when(productService.getProductDetails(Mockito.anyString())).thenReturn(product);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/products/name/productName")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

		verify(productService).getProductDetails("productName");
	}

	@Test
	public void testDeleteProductDetails() throws Exception {

		Mockito.when(productService.deleteProductDetails(Mockito.anyString())).thenReturn("Success");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/products/name/productName")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

		System.out.println(result.getResponse().getContentAsString());

		assertEquals("Success", result.getResponse().getContentAsString());

		verify(productService).deleteProductDetails("productName");

	}
}
