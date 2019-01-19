package com.cg.rest.firstrest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cg.rest.firstrest.address.Address;
import com.cg.rest.firstrest.employee.Employee;
import com.cg.rest.firstrest.employee.repository.EmployeeRepository;

@SpringBootApplication
public class FirstRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstRestApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateData(EmployeeRepository repository)
	{
		return (arg) -> {
			repository.save(new Employee(101,"Srivani",34000,new Address(5,"Karimnagar",505001)));
			repository.save(new Employee(102,"Srivani",34000,new Address(5,"Karimnagar",505001)));
			repository.save(new Employee(103,"Srivani",34000,new Address(5,"Karimnagar",505001)));
		};
	}
		
}

