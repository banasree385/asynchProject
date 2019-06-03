package com.ban.asynchProject.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ban.asynchProject.model.Employee;

public interface EmployeeService {
	
	
	public List<Employee> getEmployees();
	public Optional<Employee> getEmployeeById(Long id);
	public Map<Long,List<Employee>> getEmployeeGroupByDept();


}
