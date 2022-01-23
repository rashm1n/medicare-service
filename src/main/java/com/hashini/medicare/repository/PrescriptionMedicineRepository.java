package com.hashini.medicare.repository;

import com.hashini.medicare.model.PrescriptionMedicine;
import com.hashini.medicare.model.PrescriptionMedicineKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionMedicineRepository extends JpaRepository<PrescriptionMedicine, PrescriptionMedicineKey> {
}