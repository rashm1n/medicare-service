package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.PatientDAO;
import com.hashini.medicare.dto.PatientDTO;
import com.hashini.medicare.mapper.PatientMapper;
import com.hashini.medicare.mapper.PatientRowMapper;
import com.hashini.medicare.model.Patient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public List<PatientDTO> findAllPatients(Optional<String> searchTerm,
                                            Optional<String> regNo,
                                            int cityId) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM patient WHERE city_id = ? ");
        params.add(cityId);
//        boolean firstCondition = true;


        if (searchTerm.isPresent()) {
            sql.append(" AND ((LOWER(patient.name) LIKE ? ) OR (LOWER(patient.nic) LIKE ? ) OR (LOWER(patient.address) LIKE ?)) ");
            params.add("%" + searchTerm.get().toLowerCase() + "%");
            params.add("%" + searchTerm.get().toLowerCase() + "%");
            params.add("%" + searchTerm.get().toLowerCase() + "%");
//            firstCondition = false;
        }

        if (regNo.isPresent()) {
//            if (!firstCondition) {
//                sql.append(" AND ");
//            } else {
//                sql.append(" WHERE ");
//            }
            sql.append("AND LOWER(patient.reg_no) LIKE ?");
            params.add("%" + regNo.get().toLowerCase() + "%");
        }
        return jdbcTemplate.query(sql.toString(), new PatientRowMapper(), params.toArray());
    }

    @Override
    public Optional<PatientDTO> selectPatientById(long id, int cityId) {
        String sql = "SELECT p.patient_id, p.reg_no, p.name, p.age, p.gender, p.nic, p.address, " +
                "p.tp_number, p.allergies, p.created_date, p.updated_date, pr.prescription_id, pr.patient_id, " +
                "pr.diagnosis, pr.history, pr.processed, pr.created_date AS prescription_created_date " +
                "FROM patient p LEFT JOIN prescription pr on p.patient_id = pr.patient_id " +
                "WHERE p.patient_id = ? AND p.city_id = ? ";
        return new ArrayList<>(Objects.requireNonNull(jdbcTemplate.query(sql, new PatientMapper(), id, cityId)).values())
                .stream()
                .findFirst();
    }

    @Override
    @Transactional
    public PatientDTO addPatient(Patient patient,
                                 int cityId) {
        String sql = "INSERT INTO patient(reg_no,name,age,gender,nic,address,tp_number,allergies,city_id) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String registerNumber = String.format("BKE%06d",
                jdbcTemplate.queryForObject("SELECT nextval('patient_reg_no_seq')", Integer.class));
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"patient_id"});
            ps.setString(1, registerNumber);
            ps.setString(2, patient.getName());
            ps.setInt(3, patient.getAge());
            ps.setString(4, patient.getGender());
            ps.setString(5, patient.getNic());
            ps.setString(6, patient.getAddress());
            ps.setInt(7, patient.getTpNumber());
            ps.setString(8, patient.getAllergies());
            ps.setInt(9, cityId);
            return ps;
        }, keyHolder);

        long patientId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findPatientById(patientId);
    }

    @Override
    public PatientDTO updatePatient(Patient patient, long id) {
        String sql = "UPDATE patient SET name = ?, age = ?, gender = ?, tp_number = ?, nic = ?, address = ?, " +
                "allergies = ?, updated_date = CURRENT_TIMESTAMP WHERE patient_id = ?";
        jdbcTemplate.update(sql, patient.getName(), patient.getAge(), patient.getGender(),
                patient.getTpNumber(), patient.getNic(), patient.getAddress(), patient.getAllergies(), id);
        return findPatientById(id);
    }

    @Override
    public int deletePatient(long id) {
        String sql = "DELETE FROM patient WHERE patient_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private PatientDTO findPatientById(long patientId) {
        String sql = "SELECT patient_id, reg_no, name, age, gender, nic, address, tp_number, " +
                "allergies, created_date, updated_date FROM patient WHERE patient_id = ?";
        return jdbcTemplate.queryForObject(sql, new PatientRowMapper(), patientId);
    }
}