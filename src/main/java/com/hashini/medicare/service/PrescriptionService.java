package com.hashini.medicare.service;

import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.model.Medicine;
import com.hashini.medicare.model.Patient;
import com.hashini.medicare.model.Prescription;
import com.hashini.medicare.model.PrescriptionMedicine;
import com.hashini.medicare.repository.MedicineRepository;
import com.hashini.medicare.repository.PatientRepository;
import com.hashini.medicare.repository.PrescriptionMedicineRepository;
import com.hashini.medicare.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;
    private final MedicineRepository medicineRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
                               PatientRepository patientRepository,
                               MedicineRepository medicineRepository,
                               PrescriptionMedicineRepository prescriptionMedicineRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
        this.prescriptionMedicineRepository = prescriptionMedicineRepository;
    }

    public PrescriptionDTO addPrescription(PrescriptionDTO prescriptionInfo) throws Exception {
        Patient patient = patientRepository.findById(prescriptionInfo.getPatientId()).orElseThrow(() ->
                new NotFoundException("Patient with id =" + prescriptionInfo.getPatientId() + "is not found"));
        Prescription prescription = prescriptionRepository.save(new Prescription(patient, prescriptionInfo.getDate()));
        List<PrescriptionMedicine> prescriptionMedicines = prescriptionInfo.getMedicines().stream()
                .map(item -> {
                    Medicine medicine = medicineRepository.findByName(item.getMedicineName());
                    return new PrescriptionMedicine(prescription, medicine, item.getDose(), item.getFrequency(),
                            item.getDuration(), item.getAdditionalInfo());
                }).collect(Collectors.toList());
        prescriptionMedicineRepository.saveAll(prescriptionMedicines);
        return prescriptionInfo;
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Prescription getPrescription(long id) throws Exception {
        return prescriptionRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Prescription with id = " + id + "not found"));
    }
}
