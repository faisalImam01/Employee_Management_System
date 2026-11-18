package com.example.demo2.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo2.model.Employee;

@Repository
public interface EmployeeRepositry  extends JpaRepository<Employee, Long>{

}
