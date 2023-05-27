package com.scottslatton.skillhub.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trainings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String content;

    @Min(value = 1, message = "Estimated duration must be at least 1")
    private int estimatedDuration;

    @Future(message = "Due date must be a future date")
    private LocalDate dueDate;

    @Column(name = "experience")
    private int experience;

    private boolean completed;

    @JsonBackReference
    @ManyToMany(mappedBy = "trainings", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Employee> employees = new HashSet<>();

}

