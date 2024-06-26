package com.hashini.medicare.dao;

import com.hashini.medicare.dto.PatientDTO;
import com.hashini.medicare.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDAO {

    List<PatientDTO> findAllPatients(Optional<String> searchTerm,
                                     Optional<String> regNo,
                                     int cityId);

    PatientDTO addPatient(Patient patient,
                          int cityId);

    PatientDTO updatePatient(Patient patient, long id);

    Optional<PatientDTO> selectPatientById(long id, int cityId);

    int deletePatient(long id);
}
