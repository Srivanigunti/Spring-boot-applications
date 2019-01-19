package com.cg.rest.secondrest.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.rest.secondrest.entity.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer>{

}
