package com.example.datajpademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.datajpademo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);

	Integer deleteByName(String name);

}
