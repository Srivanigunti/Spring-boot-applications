package com.cg.rest.secondrest.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cg.rest.secondrest.customer.repository.CustomerRepository;
import com.cg.rest.secondrest.entity.Customer;


@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository repository;
	
	@Override
	public void addNewCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

}
