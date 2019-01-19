package com.cg.rest.secondrest.employee.service;

import java.util.List;

import com.cg.rest.secondrest.entity.Customer;

public interface CustomerService {

	void addNewCustomer(Customer customer);

	List<Customer> getAllCustomers();

}