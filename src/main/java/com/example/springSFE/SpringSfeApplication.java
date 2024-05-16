package com.example.springSFE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.springSFE", "com.example.springSFE.Repository"})
public class SpringSfeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSfeApplication.class, args);
	}

}
