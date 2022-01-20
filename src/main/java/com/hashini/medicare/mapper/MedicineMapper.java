package com.hashini.medicare.mapper;

import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.model.Medicine;
import org.springframework.stereotype.Component;

@Component
public class MedicineMapper {

    public Medicine toMedicine(MedicineDTO medicineDTO) {
        return new Medicine(medicineDTO.getName(), medicineDTO.getUnitPrice(), medicineDTO.getUnits());
    }

    public MedicineDTO toMedicineDTO(Medicine medicine) {
        return new MedicineDTO(medicine.getName(), medicine.getUnitPrice(), medicine.getUnits());
    }
}