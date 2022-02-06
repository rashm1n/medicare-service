package com.hashini.medicare.service;

import com.hashini.medicare.dao.MedicineDAO;
import com.hashini.medicare.dao.PatientDAO;
import com.hashini.medicare.dao.PrescriptionDAO;
import com.hashini.medicare.dao.PrescriptionMedicineDAO;
import com.hashini.medicare.dto.PrescriptionCreationDTO;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.model.Prescription;
import com.hashini.medicare.model.PrescriptionMedicine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    private final PrescriptionDAO prescriptionDAO;
    private final PatientDAO patientDAO;
    private final PrescriptionMedicineDAO prescriptionMedicineDAO;
    private final MedicineDAO medicineDAO;

    public PrescriptionService(PrescriptionDAO prescriptionDAO,
                               PatientDAO patientDAO,
                               PrescriptionMedicineDAO prescriptionMedicineDAO,
                               MedicineDAO medicineDAO) {
        this.prescriptionDAO = prescriptionDAO;
        this.patientDAO = patientDAO;
        this.prescriptionMedicineDAO = prescriptionMedicineDAO;
        this.medicineDAO = medicineDAO;
    }

    @Transactional(rollbackFor={NotFoundException.class})
    public long addPrescription(PrescriptionCreationDTO prescriptionInfo) {
        return patientDAO.selectPatientById(prescriptionInfo.getPatientId())
                .map(patient -> {
                    long prescriptionId = prescriptionDAO.addPrescription(new Prescription(prescriptionInfo.getPatientId(),
                            prescriptionInfo.getDate(),
                            prescriptionInfo.getDiagnosis()));
                    prescriptionInfo.getMedicines()
                            .forEach(medicine -> medicineDAO.selectMedicineByName(medicine.getMedicineName())
                                    .map(medicineDTO -> prescriptionMedicineDAO.addPrescriptionMedicine(
                                            new PrescriptionMedicine(prescriptionId,
                                                    medicineDTO.getId(),
                                                    medicine.getDose(),
                                                    medicine.getDuration(),
                                                    medicine.getFrequency(),
                                                    medicine.getQuantity(),
                                                    medicine.getAdditionalInfo())))
                                    .orElseThrow(() -> new NotFoundException("Medicine name = " + medicine.getMedicineName() + " not found")));
                    return prescriptionId;
                })
                .orElseThrow(() -> new NotFoundException("Patient with id = " + prescriptionInfo.getPatientId() + " is not found"));
    }

    public List<PrescriptionDTO> getAllPrescriptions(Optional<Boolean> processed) {
        return processed.map(prescriptionDAO::selectPrescriptionsByProcessed)
                .orElseGet(prescriptionDAO::selectPrescriptions);
    }

    public PrescriptionDTO getPrescription(long id) {
        return prescriptionDAO.selectPrescriptionById(id)
                .orElseThrow(() -> new NotFoundException("Prescription with id = " + id + " not found"));
    }
}
