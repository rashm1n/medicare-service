package com.hashini.medicare.dao;

import com.hashini.medicare.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDAO {

    List<Patient> selectPatients();

    List<Patient> selectPatientsByName(String patientName);

    int addPatient(Patient patient);

    int updatePatient(Patient patient,long id);

    Optional<Patient> selectPatientById(long id);

    int deleteMovie(long id);
}
