package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.PatientDAO;
import com.hashini.medicare.mapper.PatientMapper;
import com.hashini.medicare.model.Patient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PatientDAOImpl implements PatientDAO {

    private final JdbcTemplate jdbcTemplate;

    public PatientDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Patient> selectPatients() {
        String sql = "SELECT * FROM patient";
        return jdbcTemplate.query(sql, new PatientMapper());
    }

    @Override
    public Optional<Patient> selectPatientById(long id) {
        String sql = "SELECT * FROM patient WHERE patient_id = ?";
        return jdbcTemplate.query(sql, new PatientMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public List<Patient> selectPatientsByName(String patientName) {
        String sql = "SELECT * FROM patient WHERE LOWER(patient_name) LIKE '%" + patientName.toLowerCase() + "%'";
        return jdbcTemplate.query(sql, new PatientMapper());
    }

    @Override
    public int addPatient(Patient patient) {
        String sql = "INSERT INTO patient(patient_name,age,gender) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, patient.getName(), patient.getAge(), patient.getGender());
    }

    @Override
    public int updatePatient(Patient patient, long id) {
        String sql = "UPDATE patient SET patient_name = ?, age = ?, gender = ? WHERE patient_id = ?";
        return jdbcTemplate.update(sql, patient.getName(), patient.getAge(), patient.getGender(), id);
    }

    @Override
    public int deleteMovie(long id) {
        String sql = "DELETE FROM patient WHERE patient_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}