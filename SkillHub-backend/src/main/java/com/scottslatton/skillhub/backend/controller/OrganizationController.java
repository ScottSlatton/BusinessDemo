package com.scottslatton.skillhub.backend.controller;

import com.scottslatton.skillhub.backend.model.Organization;
import com.scottslatton.skillhub.backend.repository.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationRepo organizationRepo;

    @GetMapping
    public List<Organization> getAllOrganizations() {
        return organizationRepo.findAll();
    }

    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable Long id) {
        return organizationRepo.findById(id).orElse(null);
    }

    @PostMapping
    public Organization createOrganization(@Valid @RequestBody Organization organization) {
        return organizationRepo.save(organization);
    }

    @PutMapping("/{id}")
    public Organization updateOrganization(@PathVariable Long id, @Valid @RequestBody Organization updatedOrganization) {
        Organization organization = organizationRepo.findById(id).orElse(null);
        if (organization != null) {
            organization.setName(updatedOrganization.getName());
            // Update other fields as needed
            return organizationRepo.save(organization);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable Long id) {
        organizationRepo.deleteById(id);
    }
}
