package com.cg.app.employee.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cg.app.employee.pojo.Employee;

@Component
public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Employee employee = (Employee) target;
		if(employee.getEmpName().length()<2) {
			errors.rejectValue("empName", "empname.length","Employee name must have 2 characters");
		}
		if(employee.getSalary()<100000) {
			errors.rejectValue("salary","salary.minimum","Salary must be higher than 100000");
		}
	}

}
