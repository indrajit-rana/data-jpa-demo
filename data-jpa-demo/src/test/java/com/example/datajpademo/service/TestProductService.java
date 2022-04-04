package com.example.datajpademo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.datajpademo.entities.Product;
import com.example.datajpademo.repositories.ProductRepository;
import com.example.datajpademo.services.ProductService;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class TestProductService {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	@Test
	public void testSaveProduct() {
		Product product = Product.builder().name("A").price(new BigDecimal(10)).build();

		Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

		Product savedProduct = productRepository.save(product);

		assertEquals(product.getName(), savedProduct.getName());
		Mockito.verify(productRepository).save(product);
	}

	@Test
	public void testFetchProductsDetails() {
		List<Product> products = List.of(Product.builder().name("A").price(new BigDecimal(10)).build(),
				Product.builder().name("B").price(new BigDecimal(100)).build());

		Mockito.when(productRepository.findAll()).thenReturn(products);

		List<Product> findAllProducts = productRepository.findAll();

		assertThat(findAllProducts.size()).isGreaterThan(0);

		Mockito.verify(productRepository).findAll();
	}

	@Test
	public void testGetProductDetails() {
		Product product = Product.builder().name("A").price(new BigDecimal(10)).build();

		Mockito.when(productRepository.findByName(Mockito.anyString())).thenReturn(product);

		Product findProduct = productRepository.findByName("productName");

		assertEquals(product.getName(), findProduct.getName());
		Mockito.verify(productRepository).findByName("productName");
	}
	
	@Test
	public void testDeleteProductDetails() {

		Mockito.when(productRepository.deleteByName(Mockito.anyString())).thenReturn(1);

		Integer deleteByName = productRepository.deleteByName("productName");
		
		assertEquals(1, deleteByName);
		
		Mockito.verify(productRepository).deleteByName("productName");
	}

}
