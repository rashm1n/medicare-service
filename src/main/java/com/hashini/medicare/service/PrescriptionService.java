package com.hashini.medicare.service;

import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.model.Medicine;
import com.hashini.medicare.model.Patient;
import com.hashini.medicare.model.Prescription;
import com.hashini.medicare.repository.MedicineRepository;
import com.hashini.medicare.repository.PatientRepository;
import com.hashini.medicare.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;
    private final MedicineRepository medicineRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
                               PatientRepository patientRepository,
                               MedicineRepository medicineRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
    }

    public Prescription addPrescription(PrescriptionDTO prescriptionInfo) throws Exception {
        long patientId = prescriptionInfo.getPatientId();
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new NotFoundException("Patient with id =" + patientId + "is not found"));
        Set<Medicine> medicines = prescriptionInfo.getMedicines().stream().map(item -> medicineRepository.findByName(item).get())
                .collect(Collectors.toSet());
        Prescription newPrescription = new Prescription();
        newPrescription.setDate(prescriptionInfo.getDate());
        newPrescription.setPatient(patient);
//        newPrescription.setMedicines(medicines);
        return prescriptionRepository.save(newPrescription);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Prescription getPrescription(long id) throws Exception {
        return prescriptionRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Prescription with id = " + id + "not found"));
    }
}
