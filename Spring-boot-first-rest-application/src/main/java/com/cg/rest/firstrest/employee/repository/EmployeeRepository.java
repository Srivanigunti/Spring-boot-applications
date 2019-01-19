package com.cg.rest.firstrest.employee.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.rest.firstrest.employee.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
