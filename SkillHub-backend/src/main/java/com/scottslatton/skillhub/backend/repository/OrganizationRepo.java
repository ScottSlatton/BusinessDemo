package com.scottslatton.skillhub.backend.repository;

import com.scottslatton.skillhub.backend.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepo extends JpaRepository<Organization,Long> {
}
