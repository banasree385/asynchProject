package com.ban.asynchProject.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ban.asynchProject.Dao.EmployeeDao;
import com.ban.asynchProject.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	 @Autowired
	 EmployeeDao employeeDao;

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		 return employeeDao.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		return employeeDao.findById(id);
	}

	@Override
	public Map<Long,List<Employee>> getEmployeeGroupByDept() {
		// TODO Auto-generated method stub
		List<Employee> empList=employeeDao.findAll();
		//Map<Long,List<Employee>> empMap=empList.stream().collect(Collectors.groupingBy(Employee::getDepartmentId));
		Map<Long,List<Employee>> empMap=empList.stream()
				.filter(emp->emp.getDepartmentId()!=null)
				.collect(Collectors.groupingBy(Employee::getDepartmentId));
        Map<Long,List<Employee>> finalMap = new LinkedHashMap<>();

		empMap.entrySet().stream()
		.sorted(Map.Entry.comparingByKey())
		.forEachOrdered(e->finalMap.put(e.getKey(), e.getValue()));;
		return empMap;
	}
	
	
	

}
