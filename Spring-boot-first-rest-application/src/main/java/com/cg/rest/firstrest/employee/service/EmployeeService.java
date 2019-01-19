package com.cg.rest.firstrest.employee.service;

import java.util.List;

import com.cg.rest.firstrest.employee.Employee;

public interface EmployeeService {

	void addNewEmployee(Employee employee);

	List<Employee> getAllEmployee();

	void updateEmployee(Employee employee);
	
	void deleteEmployee(Employee employee);
}