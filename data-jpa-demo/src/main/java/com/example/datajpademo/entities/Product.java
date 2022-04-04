package com.example.datajpademo.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@Column
	@SequenceGenerator(name = "product_id_sequence",sequenceName = "product_id_sequence",initialValue = 100,allocationSize = 2)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_id_sequence")
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	
	public Product(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}
}
