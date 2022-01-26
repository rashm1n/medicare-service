package com.hashini.medicare.repository;

import com.hashini.medicare.model.MedicineType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicineTypeRepository extends JpaRepository<MedicineType, Long> {
    Optional<MedicineType> findByName(String name);
}