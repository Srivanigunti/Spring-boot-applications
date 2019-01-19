package com.cg.app.employee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.app.employee.pojo.Employee;
import com.cg.app.sqlqueries.SqlQueries;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public void addNewEmployee(Employee employee) {
		 template.update(SqlQueries.getInstance().insertEmployee(),new Object[] {employee.getEmpId(),employee.getEmpName(),employee.getSalary()});
	}
}
