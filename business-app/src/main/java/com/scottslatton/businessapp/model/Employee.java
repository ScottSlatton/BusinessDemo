package com.scottslatton.businessapp.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee employee = (Employee) o;
        return getId() != null && Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
