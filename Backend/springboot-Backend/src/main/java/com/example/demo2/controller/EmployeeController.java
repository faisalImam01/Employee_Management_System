package com.example.demo2.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo2.exception.ResourceNotFoundException;
import com.example.demo2.model.Employee;
import com.example.demo2.repositry.EmployeeRepositry;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")

public class EmployeeController {

	@Autowired
	private EmployeeRepositry employeeRepositry;
	
	//get all employees
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		return employeeRepositry.findAll();
	}
	// create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepositry.save(employee);
	}
	
	//get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee=employeeRepositry.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not Exist with id :"+id));
	return ResponseEntity.ok(employee);
	
	}
	// Update employee rest api
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
		
		Employee employee=employeeRepositry.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not Exist with id :"+id));
		

		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		Employee updatedEmployee =employeeRepositry.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	// Delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		
		Employee employee=employeeRepositry.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Employee Not Exist with id :"+id));
	
   employeeRepositry.delete(employee);
   Map<String ,Boolean> response =new HashMap<>();
   response.put("deleted", Boolean.TRUE);
   return ResponseEntity.ok(response);
	} 
}
