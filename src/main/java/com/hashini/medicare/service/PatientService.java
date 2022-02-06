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
        if (patientName.isPresent()) {
            return patientDAO.selectPatientsByName(patientName.get());
        } else {
            return patientDAO.selectPatients();
        }
    }

    public Patient getPatient(long id) throws NotFoundException {
        return patientDAO.selectPatientById(id)
                .orElseThrow(() -> new NotFoundException("Patient id = " + id + " not found"));
    }

    public int addPatient(Patient patient) {
        return patientDAO.addPatient(patient);
    }

    public int updatePatient(Patient newPatient, long patientId) {
        Optional<Patient> patient = patientDAO.selectPatientById(patientId);
        if (patient.isPresent()) {
            return patientDAO.updatePatient(newPatient, patientId);
        } else {
            newPatient.setId(patientId);
            return patientDAO.addPatient(newPatient);
        }
    }

    public int deleteMovie(long id) throws NotFoundException {
        Optional<Patient> patient = patientDAO.selectPatientById(id);
        if (patient.isPresent()) {
            return patientDAO.deleteMovie(id);
        } else {
            throw new NotFoundException("Patient id = " + id + " not found");
        }
    }
}