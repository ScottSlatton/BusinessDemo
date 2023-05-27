package com.scottslatton.skillhub.backend.controller;

import com.scottslatton.skillhub.backend.exception.ResourceNotFoundException;
import com.scottslatton.skillhub.backend.model.Employee;
import com.scottslatton.skillhub.backend.model.Training;
import com.scottslatton.skillhub.backend.repository.TrainingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/trainings")
public class TrainingController {

    @Autowired
    private final TrainingRepo trainingRepo;

    public TrainingController(TrainingRepo trainingRepo) {
        this.trainingRepo = trainingRepo;
    }

    @GetMapping
    public List<Training> getAllTrainings(){
        return trainingRepo.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id){
        Training training = trainingRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Training was not found."));
        return ResponseEntity.ok(training);
    }
    @PostMapping
    public ResponseEntity<?> createTraining(@Valid @RequestBody Training training, BindingResult result) {
        if (result.hasErrors()) {
            // Handle validation errors and return appropriate response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        // If validation passes, proceed with saving the training
        Training savedTraining = trainingRepo.save(training);
        // Return success response
        return ResponseEntity.ok("Training created successfully");
    }

    @GetMapping("/{trainingId}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByTraining(@PathVariable Long trainingId) {
        Training training = trainingRepo.findById(trainingId)
                .orElseThrow(() -> new EntityNotFoundException("Training not found with id: " + trainingId));

        List<Employee> employees = new ArrayList<>(training.getEmployees());

        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTrainingById(@PathVariable Long id){
        Training training = trainingRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Training was not found"));

        trainingRepo.delete(training);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
