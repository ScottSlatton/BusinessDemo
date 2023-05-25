package com.scottslatton.businessapp.controller;

import com.scottslatton.businessapp.repository.EmployeeRepo;
import com.scottslatton.businessapp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }
}
