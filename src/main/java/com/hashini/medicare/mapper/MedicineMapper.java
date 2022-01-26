package com.hashini.medicare.mapper;

import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.model.Medicine;
import com.hashini.medicare.model.MedicineType;
import org.springframework.stereotype.Component;

@Component
public class MedicineMapper {

    public Medicine toMedicine(MedicineDTO medicineDTO, MedicineType type) {
        return new Medicine(medicineDTO.getName(), medicineDTO.getUnitPrice(), medicineDTO.getUnits(), type);
    }

    public MedicineDTO toMedicineDTO(Medicine medicine) {
        return new MedicineDTO(medicine.getId(), medicine.getName(), medicine.getUnitPrice(), medicine.getUnits(),
                medicine.getMedicineType().getName());
    }
}