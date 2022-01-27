package com.hashini.medicare.repository;

import com.hashini.medicare.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findByProcessed(Boolean processed);
}
