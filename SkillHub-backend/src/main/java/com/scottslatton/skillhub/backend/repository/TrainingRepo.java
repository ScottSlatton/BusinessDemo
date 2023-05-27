package com.scottslatton.skillhub.backend.repository;

import com.scottslatton.skillhub.backend.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepo extends JpaRepository<Training, Long> {
}
