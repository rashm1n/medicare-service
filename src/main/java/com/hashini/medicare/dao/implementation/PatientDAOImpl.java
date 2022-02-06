package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.PatientDAO;
import com.hashini.medicare.mapper.PatientRowMapper;
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
        return jdbcTemplate.query(sql, new PatientRowMapper());
    }

    @Override
    public Optional<Patient> selectPatientById(long id) {
        String sql = "SELECT * FROM patient WHERE id = ?";
        return jdbcTemplate.query(sql, new PatientRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public List<Patient> selectPatientsByName(String patientName) {
        String sql = "SELECT * FROM patient WHERE LOWER(name) LIKE '%" + patientName.toLowerCase() + "%'";
        return jdbcTemplate.query(sql, new PatientRowMapper());
    }

    @Override
    public int addPatient(Patient patient) {
        String sql = "INSERT INTO patient(name,age,gender) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, patient.getName(), patient.getAge(), patient.getGender());
    }

    @Override
    public int updatePatient(Patient patient, long id) {
        String sql = "UPDATE patient SET name = ?, age = ?, gender = ? WHERE id = ?";
        return jdbcTemplate.update(sql, patient.getName(), patient.getAge(), patient.getGender(), id);
    }

    @Override
    public int deleteMovie(long id) {
        String sql = "DELETE FROM patient WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}