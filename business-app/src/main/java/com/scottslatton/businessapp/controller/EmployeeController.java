package com.scottslatton.businessapp.controller;

import com.scottslatton.businessapp.exception.ResourceNotFoundException;
import com.scottslatton.businessapp.repository.EmployeeRepo;
import com.scottslatton.businessapp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepo.save(employee);
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){

        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee was not found."));

        return ResponseEntity.ok(employee);
    }
}
