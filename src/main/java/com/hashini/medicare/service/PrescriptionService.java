package com.hashini.medicare.service;

import com.hashini.medicare.dao.PatientDAO;
import com.hashini.medicare.dao.PrescriptionDAO;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.model.Patient;
import com.hashini.medicare.model.Prescription;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    private final PrescriptionDAO prescriptionDAO;
    private final PatientDAO patientDAO;

    public PrescriptionService(PrescriptionDAO prescriptionDAO,
                               PatientDAO patientDAO) {
        this.prescriptionDAO = prescriptionDAO;
        this.patientDAO = patientDAO;
    }

    public int addPrescription(Prescription prescription) throws Exception {
        Optional<Patient> patient = patientDAO.selectPatientById(prescription.getPatientId());
        if (patient.isPresent()) {
            return prescriptionDAO.addPrescription(prescription);
        } else {
            throw new NotFoundException("Patient with id = " + prescription.getPatientId() + " is not found");
        }
    }

    public List<PrescriptionDTO> getAllPrescriptions(Optional<Boolean> processed) {
        if (processed.isPresent()) {
            return prescriptionDAO.selectPrescriptionsByProcessed(processed.get());
        } else {
            return prescriptionDAO.selectPrescriptions();
        }
    }

    public PrescriptionDTO getPrescription(long id) throws Exception {
        return prescriptionDAO.selectPrescriptionById(id)
                .orElseThrow(() -> new NotFoundException("Prescription with id = " + id + "not found"));
    }
}
