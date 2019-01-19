package com.cg.rest.secondrest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cg.rest.secondrest.customer.repository.CustomerRepository;
import com.cg.rest.secondrest.entity.Address;
import com.cg.rest.secondrest.entity.Customer;

@SpringBootApplication
public class SecondRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondRestApplication.class, args);
	}

	
}

