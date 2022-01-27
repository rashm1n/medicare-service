package com.hashini.medicare.service;

import com.hashini.medicare.dto.PrescriptionCreationDTO;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.mapper.PrescriptionMapper;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;
    private final MedicineRepository medicineRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;
    private final PrescriptionMapper prescriptionMapper;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
                               PatientRepository patientRepository,
                               MedicineRepository medicineRepository,
                               PrescriptionMedicineRepository prescriptionMedicineRepository,
                               PrescriptionMapper prescriptionMapper) {

        this.prescriptionRepository = prescriptionRepository;
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
        this.prescriptionMedicineRepository = prescriptionMedicineRepository;
        this.prescriptionMapper = prescriptionMapper;
    }

    public PrescriptionCreationDTO addPrescription(PrescriptionCreationDTO prescriptionInfo) throws Exception {
        Patient patient = patientRepository.findById(prescriptionInfo.getPatientId()).orElseThrow(() ->
                new NotFoundException("Patient with id = " + prescriptionInfo.getPatientId() + " is not found"));
        Prescription prescription = prescriptionRepository.save(new Prescription(patient, prescriptionInfo.getDate()));
        List<PrescriptionMedicine> prescriptionMedicines = prescriptionInfo.getMedicines().stream()
                .map(item -> {
                    Medicine medicine = medicineRepository.findByName(item.getMedicineName());
                    return new PrescriptionMedicine(prescription, medicine, item.getDose(), item.getFrequency(),
                            item.getDuration(), item.getAdditionalInfo(), item.getQuantity());
                }).collect(Collectors.toList());
        prescriptionMedicineRepository.saveAll(prescriptionMedicines);
        return prescriptionInfo;
    }

    public List<PrescriptionDTO> getAllPrescriptions(Optional<Boolean> processed) {
        return processed.map(prescriptionRepository::findByProcessed).orElseGet(prescriptionRepository::findAll)
                .stream().map(prescriptionMapper::toPrescriptionDTO).collect(Collectors.toList());
    }

    public Prescription getPrescription(long id) throws Exception {
        return prescriptionRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Prescription with id = " + id + "not found"));
    }
}
