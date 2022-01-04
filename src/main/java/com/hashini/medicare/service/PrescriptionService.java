package com.hashini.medicare.service;

import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.model.Patient;
import com.hashini.medicare.model.Prescription;
import com.hashini.medicare.repository.PatientRepository;
import com.hashini.medicare.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
                               PatientRepository patientRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.patientRepository = patientRepository;
    }

    public Prescription addPrescription(PrescriptionDTO prescriptionInfo) {
        long patientId = prescriptionInfo.getPatientId();
        Patient patient = patientRepository.findById(patientId).get();
        Prescription newPrescription = new Prescription();
        newPrescription.setDate(prescriptionInfo.getDate());
        newPrescription.setPatient(patient);
        return prescriptionRepository.save(newPrescription);
    }

    public List<Prescription> getAllPatients() {
        return prescriptionRepository.findAll();
    }
}
