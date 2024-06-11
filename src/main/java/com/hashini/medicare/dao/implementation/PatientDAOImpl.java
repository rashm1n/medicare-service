package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.PatientDAO;
import com.hashini.medicare.dto.PatientDTO;
import com.hashini.medicare.mapper.PatientMapper;
import com.hashini.medicare.model.Patient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PatientDAOImpl implements PatientDAO {

    private final JdbcTemplate jdbcTemplate;

    public PatientDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PatientDTO> selectPatients() {
        String sql = "SELECT * " +
                "FROM patient " +
                "LEFT JOIN prescription p on patient.patient_id = p.patient_id " +
                "ORDER BY patient.patient_id DESC";
        return new ArrayList<>((Objects.requireNonNull(jdbcTemplate.query(sql, new PatientMapper()))).values());
    }

    @Override
    public Optional<PatientDTO> selectPatientById(long id) {
        String sql = "SELECT p.patient_id, p.patient_code, p.patient_name, p.age, p.gender, p.nic, p.address, " +
                "p.tp_number, p.allergies, p.created_date, pr.prescription_id, pr.patient_id, pr.diagnosis, pr.history, " +
                "pr.processed, pr.created_date AS prescription_created_date " +
                "FROM patient p " +
                "LEFT JOIN prescription pr on p.patient_id = pr.patient_id " +
                "WHERE p.patient_id = ?";
        return new ArrayList<>(Objects.requireNonNull(jdbcTemplate.query(sql, new PatientMapper(), id)).values())
                .stream()
                .findFirst();
    }

    @Override
    public List<PatientDTO> selectPatientsBySearchTerm(String searchTerm) {
        String searchString = "%" + searchTerm.toLowerCase() + "%";
        String sql = "SELECT * " +
                "FROM patient " +
                "LEFT JOIN prescription p on patient.patient_id = p.patient_id " +
                "WHERE (LOWER(patient.patient_name) LIKE ? ) OR (LOWER(patient.nic) LIKE ? ) OR " +
                "(LOWER(patient.address) LIKE ? ) ORDER BY patient.patient_id DESC";
        return new ArrayList<>((Objects.requireNonNull(jdbcTemplate.query(sql, new PatientMapper(),
                searchString, searchString, searchString))).values());
    }

    @Override
    public long addPatient(Patient patient) {
        String sql = "INSERT INTO patient(patient_code,patient_name,age,gender,nic,address,tp_number,allergies) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"patient_id"});
            ps.setString(1, "1");
            ps.setString(2, patient.getName());
            ps.setInt(3, patient.getAge());
            ps.setString(4, patient.getGender());
            ps.setString(5, patient.getNic());
            ps.setString(6, patient.getAddress());
            ps.setInt(7, patient.getTpNumber());
            ps.setString(8, patient.getAllergies());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public long updatePatient(Patient patient, long id) {
        String sql = "UPDATE patient SET patient_name = ?, age = ?, gender = ?, tp_number = ?, nic = ?, address = ?, " +
                "allergies = ? WHERE patient_id = ?";
        return jdbcTemplate.update(sql, patient.getName(), patient.getAge(), patient.getGender(),
                patient.getTpNumber(), patient.getNic(), patient.getAddress(), patient.getAllergies(), id);
    }

    @Override
    public int deletePatient(long id) {
        String sql = "DELETE FROM patient WHERE patient_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}