package com.ban.asynchProject.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.ban.asynchProject.Dao.EmployeeDao;
import com.ban.asynchProject.model.Employee;
import com.mchange.util.AssertException;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
	
	@InjectMocks
	EmployeeServiceImpl service;
	
	@Mock
	EmployeeDao dao;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void getEmployeesTest(){
		List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee(1l,"bana","saha","bnsr","9611",LocalDate.now(),"11",new BigDecimal(15000),new BigDecimal(10),110l,33l);
        Employee empTwo = new Employee(2l,"bana","saha","bnsr","9611",LocalDate.now(),"11",new BigDecimal(15000),new BigDecimal(10),110l,33l);
        Employee empThree = new Employee(3l,"bana","saha","bnsr","9611",LocalDate.now(),"11",new BigDecimal(15000),new BigDecimal(10),110l,33l);
        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);
        when(dao.findAll()).thenReturn(list);
        List<Employee> empList = service.getEmployees();
        assertEquals("The employee list size ",3, empList.size());

        verify(dao, times(1)).findAll();
        
	}
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	
	@Test(expected=Exception.class)
	public void getEmployeesTestException(){
		exceptionRule.expect(NullPointerException.class);
	    exceptionRule.expectMessage("No employee found");
		List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee(1l,"bana","saha","bnsr","9611",LocalDate.now(),"11",new BigDecimal(15000),new BigDecimal(10),110l,33l);
        Employee empTwo = new Employee(2l,"bana","saha","bnsr","9611",LocalDate.now(),"11",new BigDecimal(15000),new BigDecimal(10),110l,33l);
        Employee empThree = new Employee(3l,"bana","saha","bnsr","9611",LocalDate.now(),"11",new BigDecimal(15000),new BigDecimal(10),110l,33l);
        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);
        when(dao.findAll()).thenThrow(new Exception());
        List<Employee> empList = service.getEmployees();
        verify(dao, times(1)).findAll();
        
	}

}
