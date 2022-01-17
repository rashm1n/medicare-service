package com.hashini.medicare.repository;

import com.hashini.medicare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByNameIgnoreCaseStartsWith(String name);
}