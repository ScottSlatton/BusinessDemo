package com.scottslatton.skillhub.backend.controller;

import com.scottslatton.skillhub.backend.exception.ResourceNotFoundException;
import com.scottslatton.skillhub.backend.model.Training;
import com.scottslatton.skillhub.backend.repository.EmployeeRepo;
import com.scottslatton.skillhub.backend.model.Employee;
import com.scottslatton.skillhub.backend.repository.TrainingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private TrainingRepo trainingRepo;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee){
        return employeeRepo.save(employee);
    }
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){

        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee was not found."));

        return ResponseEntity.ok(employee);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @Valid @RequestBody Employee employeeRequest){
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee was not found"));
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmailId(employeeRequest.getEmailId());

        Employee updatedEmployee = employeeRepo.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee was not found"));

        employeeRepo.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PostMapping("{employeeId}/trainings/{trainingId}")
    public ResponseEntity<String> addTrainingToEmployee(@PathVariable Long employeeId, @PathVariable Long trainingId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));

        Training training = trainingRepo.findById(trainingId)
                .orElseThrow(() -> new EntityNotFoundException("Training not found with id: " + trainingId));

        employee.getTrainings().add(training);
        employeeRepo.save(employee);

        return ResponseEntity.ok("Training added to employee successfully");
    }
}
