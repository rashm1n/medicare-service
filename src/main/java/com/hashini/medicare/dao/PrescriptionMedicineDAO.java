package com.hashini.medicare.dao;

import com.hashini.medicare.dto.MedicineQuantityDTO;
import com.hashini.medicare.dto.PrescriptionMedicineCreationDTO;
import com.hashini.medicare.dto.PrescriptionMedicineUpdateDTO;

import java.util.List;

public interface PrescriptionMedicineDAO {

    List<MedicineQuantityDTO> findByPrescriptionId(long prescriptionId);

    void updatePrescriptionMedicines(Long prescriptionId, List<PrescriptionMedicineUpdateDTO> medicines);

    void addPrescriptionMedicines(Long prescriptionId, List<PrescriptionMedicineCreationDTO> medicines);
}