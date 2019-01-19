package com.cg.rest.firstrest.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.rest.firstrest.employee.Employee;
import com.cg.rest.firstrest.employee.service.EmployeeService;

@RestController
@RequestMapping("/employees") // Addressable resource
public class EmployeeResource {

	@Autowired
	private EmployeeService service;

	@PostMapping // uniform constrain interface
	public void ddNewEmployee(@RequestBody Employee employee) {
		service.addNewEmployee(employee);
	}

	
	@RequestMapping//uniform constrain interface
	public List<Employee> getAllEmployee() {
		return service.getAllEmployee();
	}

}
