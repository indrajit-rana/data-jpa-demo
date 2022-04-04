package com.example.datajpademo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.datajpademo.dtos.ProductDto;
import com.example.datajpademo.entities.Product;
import com.example.datajpademo.exception.ProductAlreadyExistsException;
import com.example.datajpademo.exception.ProductNotFoundException;
import com.example.datajpademo.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public Product saveProduct(ProductDto productdto) {
		Product product = new Product(productdto.getName(), productdto.getPrice());
		if(productRepository.findByName(product.getName()) != null) {
			throw new ProductAlreadyExistsException();
		}
		log.info("Persisting product.");
		return productRepository.save(product);
	}

	public List<Product> fetchProductsDetails() {
		log.info("Fetching all products.");
		return productRepository.findAll(Sort.by("price").descending());
	}

	public Product getProductDetails(String name) {
		Product findByName = productRepository.findByName(name);
		if(findByName == null) {
			throw new ProductNotFoundException();
		}
		log.info("Fetching a product with the name {}.", name);
		return findByName;
	}

	public String deleteProductDetails(String name) {
		return (productRepository.deleteByName(name) == 0)?"Failure":"Success.";
	}
}
