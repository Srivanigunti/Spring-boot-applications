package com.cg.rest.secondrest.customer.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.rest.secondrest.employee.service.CustomerService;
import com.cg.rest.secondrest.entity.Customer;

@RestController
@RequestMapping("/customer") // Addressable resource
public class CustomerResource {

	@Autowired
	private CustomerService service;

	@PostMapping // uniform constrain interface
	public void AddNewCustomer(@RequestBody Customer customer) {
		service.addNewCustomer(customer);
	}

	
	@RequestMapping//uniform constrain interface
	public List<Customer> getAllCustomers() {
		return service.getAllCustomers();
	}

}
