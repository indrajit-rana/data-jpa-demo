package com.example.datajpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class DataJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJpaDemoApplication.class, args);
	}

}
