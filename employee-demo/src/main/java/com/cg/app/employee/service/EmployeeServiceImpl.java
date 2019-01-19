package com.cg.app.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.app.employee.dao.EmployeeDao;
import com.cg.app.employee.pojo.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;
	
	@Override
	public void addNewEmpoyee(Employee employee) {
		 dao.addNewEmployee(employee);
	}
	
}
