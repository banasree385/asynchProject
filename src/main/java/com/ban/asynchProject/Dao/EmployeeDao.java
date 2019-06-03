package com.ban.asynchProject.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ban.asynchProject.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long>{

}
