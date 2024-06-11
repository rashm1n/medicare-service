package com.hashini.medicare.dao;

import com.hashini.medicare.dto.PatientDTO;
import com.hashini.medicare.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDAO {

    List<PatientDTO> selectPatients();

    List<PatientDTO> selectPatientsBySearchTerm(String searchTerm);

    long addPatient(Patient patient);

    long updatePatient(Patient patient, long id);

    Optional<PatientDTO> selectPatientById(long id);

    int deletePatient(long id);
}
