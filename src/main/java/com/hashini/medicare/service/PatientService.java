package com.hashini.medicare.service;

import com.hashini.medicare.dao.PatientDAO;
import com.hashini.medicare.dto.PatientDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientDAO patientDAO;

    public PatientService(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public List<PatientDTO> getAllPatients(Optional<String> searchTerm,
                                           Optional<String> regNo,
                                           int cityId) {
        return patientDAO.findAllPatients(searchTerm, regNo, cityId);
    }

    public PatientDTO getPatient(long id, int cityId) throws NotFoundException {
        return patientDAO.selectPatientById(id, cityId)
                .orElseThrow(() -> new NotFoundException("Patient id = " + id + " not found"));
    }

    public PatientDTO addPatient(Patient patient, int cityId) {
        return patientDAO.addPatient(patient, cityId);
    }

    public PatientDTO updatePatient(Patient newPatient, long patientId, int cityId) {
        return patientDAO.selectPatientById(patientId, cityId)
                .map(patient -> patientDAO.updatePatient(newPatient, patientId))
                .orElseGet(() -> {
                    newPatient.setId(patientId);
                    try {
                        return patientDAO.addPatient(newPatient, cityId);
                    } catch (Exception e) {
                        throw new NotFoundException("City id = " + cityId + " not found");
                    }
                });
    }

    public int deletePatient(long id,
                             int cityId) throws NotFoundException {
        return patientDAO.selectPatientById(id, cityId)
                .map(patient -> patientDAO.deletePatient(id))
                .orElseThrow(() -> new NotFoundException("Patient id = " + id + " not found"));

    }
}