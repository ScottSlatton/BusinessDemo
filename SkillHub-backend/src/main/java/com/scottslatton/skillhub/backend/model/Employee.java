package com.scottslatton.skillhub.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name is required")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name is required")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email is required")
    @Email
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid email format")
    @Column(name = "email_id")
    private String emailId;

    @Column(name = "level")
    private int level;

    @Column(name = "experience")
    private int experience;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "employee_training",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "training_id")
    )
    @JsonIgnoreProperties("employees")
    private Set<Training> trainings = new HashSet<>();

    public void increaseExperience(int amount) {
        this.experience += amount;
        if (this.experience >= 1000) {
            this.level++;
            this.experience = 0; // Reset experience after reaching 1000
        }
    }

}
