package com.hashini.medicare.mapper;

import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.dto.PrescriptionMedicineDTO;
import com.hashini.medicare.model.Prescription;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PrescriptionMapper {

    private final MedicineMapper medicineMapper;

    public PrescriptionMapper(MedicineMapper medicineMapper) {
        this.medicineMapper = medicineMapper;
    }

    public PrescriptionDTO toPrescriptionDTO(Prescription prescription) {
        return new PrescriptionDTO(prescription.getPatient(), prescription.getDate(), prescription.getMedicines().stream()
                .map(medicine -> new PrescriptionMedicineDTO(medicineMapper.toMedicineDTO(medicine.getMedicine()),
                        medicine.getDose(), medicine.getFrequency(), medicine.getDuration(),
                        medicine.getAdditionalInfo(), medicine.getQuantity())).collect(Collectors.toList()));
    }
}