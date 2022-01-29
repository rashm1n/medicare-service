package com.hashini.medicare.mapper;

import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.dto.PrescriptionMedicineDTO;
import com.hashini.medicare.model.Prescription;
import com.hashini.medicare.model.PrescriptionMedicine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrescriptionMapper {

    private final MedicineMapper medicineMapper;

    public PrescriptionMapper(MedicineMapper medicineMapper) {
        this.medicineMapper = medicineMapper;
    }

    public PrescriptionDTO toPrescriptionDTO(Prescription prescription) {
        return new PrescriptionDTO(prescription.getId(),
                prescription.getPatient(),
                prescription.getDate(),
                prescription.getDiagnosis(),
                prescription.getMedicines().stream().map(this::toPrescriptionMedicineDTO).collect(Collectors.toList()));
    }

    public PrescriptionDTO toPrescriptionDTO(Prescription prescription, List<PrescriptionMedicine> medicines) {
        return new PrescriptionDTO(prescription.getId(),
                prescription.getPatient(),
                prescription.getDate(),
                prescription.getDiagnosis(),
                medicines.stream().map(this::toPrescriptionMedicineDTO).collect(Collectors.toList()));
    }

    private PrescriptionMedicineDTO toPrescriptionMedicineDTO(PrescriptionMedicine prescriptionMedicine) {
        return new PrescriptionMedicineDTO(medicineMapper.toMedicineDTO(prescriptionMedicine.getMedicine()),
                prescriptionMedicine.getDose(),
                prescriptionMedicine.getFrequency(),
                prescriptionMedicine.getDuration(),
                prescriptionMedicine.getAdditionalInfo(),
                prescriptionMedicine.getQuantity());
    }
}