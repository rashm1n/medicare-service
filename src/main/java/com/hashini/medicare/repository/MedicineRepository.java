package com.hashini.medicare.repository;

import com.hashini.medicare.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    Medicine findByName(String name);

    List<Medicine> findByNameIgnoreCaseStartsWith(String name);
}