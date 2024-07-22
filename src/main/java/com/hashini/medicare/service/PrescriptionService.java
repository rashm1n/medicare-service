package com.hashini.medicare.service;

import com.hashini.medicare.dao.MedicineDAO;
import com.hashini.medicare.dao.PatientDAO;
import com.hashini.medicare.dao.PrescriptionDAO;
import com.hashini.medicare.dao.PrescriptionMedicineDAO;
import com.hashini.medicare.dto.MedicineQuantityDTO;
import com.hashini.medicare.dto.PrescriptionCreationDTO;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.dto.PrescriptionUpdateDTO;
import com.hashini.medicare.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional(rollbackFor = {NotFoundException.class})
    public long addPrescription(PrescriptionCreationDTO prescriptionInfo,
                                int cityId) {
        return patientDAO.selectPatientById(prescriptionInfo.getPatientId(), cityId)
                .map(patient -> {
                    long prescriptionId = prescriptionDAO.addPrescription(prescriptionInfo);
                    deductInventory(prescriptionInfo.getMedicines().stream().map(medicine ->
                            new MedicineQuantityDTO(medicine.getMedicineId(), medicine.getQuantity())
                    ).collect(Collectors.toList()));
                    prescriptionMedicineDAO.addPrescriptionMedicines(prescriptionId,
                            prescriptionInfo.getMedicines());
                    return prescriptionId;
                })
                .orElseThrow(() -> new NotFoundException("Patient with id = " + prescriptionInfo.getPatientId() + " is not found"));
    }

    public List<PrescriptionDTO> getAllPrescriptions(Optional<Boolean> processed,
                                                     Optional<String> searchTerm,
                                                     LocalDateTime startDate,
                                                     LocalDateTime endDate,
                                                     int cityId) {
        return prescriptionDAO.findAllPrescriptions(processed, searchTerm, startDate, endDate, cityId);
    }

    public PrescriptionDTO getPrescription(long id,
                                           int cityId) {
        return prescriptionDAO.selectPrescriptionById(id, cityId)
                .orElseThrow(() -> new NotFoundException("Prescription with id = " + id + " not found"));
    }

    @Transactional(rollbackFor = {NotFoundException.class})
    public long updatePrescription(PrescriptionUpdateDTO updatedPrescription,
                                   long prescriptionId,
                                   int cityId) {
        return prescriptionDAO.selectPrescriptionById(prescriptionId, cityId)
                .map(prescription -> {
                    long rowsAffected = prescriptionDAO.updatePrescription(updatedPrescription, prescriptionId);
                    if (rowsAffected > 0 && updatedPrescription.getMedicines() != null) {
                        List<MedicineQuantityDTO> originalMedicines = prescriptionMedicineDAO.findByPrescriptionId(prescriptionId);
                        restoreInventory(originalMedicines);
                        deductInventory(updatedPrescription.getMedicines().stream().map(medicine ->
                                new MedicineQuantityDTO(medicine.getMedicineId(), medicine.getQuantity())
                        ).collect(Collectors.toList()));
                        prescriptionMedicineDAO.updatePrescriptionMedicines(prescriptionId,
                                updatedPrescription.getMedicines());
                    }
                    return rowsAffected;
                }).orElseThrow(() -> new NotFoundException("Prescription with id = " + prescriptionId + " not found"));
    }

    @Transactional(rollbackFor = {NotFoundException.class})
    public int deletePrescription(long id,
                                  int cityId) {
        return prescriptionDAO.selectPrescriptionById(id, cityId)
                .map(prescription -> {
                    List<MedicineQuantityDTO> originalMedicines = prescriptionMedicineDAO.findByPrescriptionId(id);
                    restoreInventory(originalMedicines);
                    return prescriptionDAO.deletePrescription(id);
                })
                .orElseThrow(() -> new NotFoundException("Prescription id = " + id + " not found"));
    }

    private void restoreInventory(List<MedicineQuantityDTO> medicines) {
        for (MedicineQuantityDTO medicine : medicines) {
            medicineDAO.updateUnits(medicine.getMedicineId(), medicine.getQuantity());
        }
    }

    private void deductInventory(List<MedicineQuantityDTO> medicines) {
        for (MedicineQuantityDTO medicine : medicines) {
            medicineDAO.updateUnits(medicine.getMedicineId(), -medicine.getQuantity());
        }
    }
}