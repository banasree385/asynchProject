package com.ban.asynchProject.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ban.asynchProject.model.Employee;
import com.ban.asynchProject.service.EmployeeService;

@RestController
public class EmployeeController {
	
	 @Autowired
	 EmployeeService employeeService;
	 
	 @RequestMapping(value = "/employees", method = RequestMethod.GET,produces = { "application/json;charset=utf-8" })
		public @ResponseBody List<Employee> getEmployees()throws Exception
		{		 
		 return employeeService.getEmployees();
		}
	 
	 @RequestMapping(value = "/employee", method = RequestMethod.GET,produces = { "application/json;charset=utf-8" })
		public @ResponseBody Optional<Employee> getEmployeeById(@RequestParam(value="id", required=true) Long id)throws Exception
		{		 
		 return employeeService.getEmployeeById(id);
		}
	 
	 @RequestMapping(value = "/employeeGroupByDept", method = RequestMethod.GET,produces = { "application/json;charset=utf-8" })
		public @ResponseBody Map<Long,List<Employee>> getEmployeeGroupByDept()throws Exception
		{		 
		 return employeeService.getEmployeeGroupByDept();
		}
	
	

}
