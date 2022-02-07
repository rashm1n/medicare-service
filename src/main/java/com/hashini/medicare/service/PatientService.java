package com.hashini.medicare.service;

import com.hashini.medicare.dao.PatientDAO;
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

    public List<Patient> getAllPatients(Optional<String> patientName) {
        return patientName.map(patientDAO::selectPatientsByName).orElseGet(patientDAO::selectPatients);
    }

    public Patient getPatient(long id) throws NotFoundException {
        return patientDAO.selectPatientById(id)
                .orElseThrow(() -> new NotFoundException("Patient id = " + id + " not found"));
    }

    public long addPatient(Patient patient) {
        return patientDAO.addPatient(patient);
    }

    public long updatePatient(Patient newPatient, long patientId) {
        return patientDAO.selectPatientById(patientId)
                .map(patient -> patientDAO.updatePatient(newPatient, patientId))
                .orElseGet(() -> {
                    newPatient.setId(patientId);
                    return patientDAO.addPatient(newPatient);
                });
    }

    public int deletePatient(long id) throws NotFoundException {
        return patientDAO.selectPatientById(id)
                .map(patient -> patientDAO.deleteMovie(id))
                .orElseThrow(() -> new NotFoundException("Patient id = " + id + " not found"));

    }
}