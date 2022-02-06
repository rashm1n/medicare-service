package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.PrescriptionDAO;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.mapper.PrescriptionRowMapper;
import com.hashini.medicare.model.Prescription;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PrescriptionDAOImpl implements PrescriptionDAO {

    private final JdbcTemplate jdbcTemplate;

    public PrescriptionDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PrescriptionDTO> selectPrescriptions() {
        String sql = "SELECT prescription.id AS prescriptionId, " +
                "prescription.date," +
                "prescription.diagnosis," +
                "patient.id AS patientId," +
                "patient.name," +
                "patient.age," +
                "patient.gender " +
                "FROM prescription " +
                "INNER JOIN patient ON prescription.patient_id = patient.id";
        return jdbcTemplate.query(sql, new PrescriptionRowMapper());
    }

    @Override
    public List<PrescriptionDTO> selectPrescriptionsByProcessed(boolean processed) {
        String sql = "SELECT prescription.id AS prescriptionId, " +
                "prescription.date," +
                "prescription.diagnosis," +
                "patient.id AS patientId," +
                "patient.name," +
                "patient.age," +
                "patient.gender " +
                "FROM prescription " +
                "INNER JOIN patient ON prescription.patient_id = patient.id " +
                "WHERE prescription.processed = ?";
        return jdbcTemplate.query(sql, new PrescriptionRowMapper(), processed);
    }

    @Override
    public Optional<PrescriptionDTO> selectPrescriptionById(long id) {
        String sql = "SELECT prescription.id AS prescriptionId, " +
                "prescription.date," +
                "prescription.diagnosis," +
                "patient.id AS patientId," +
                "patient.name," +
                "patient.age," +
                "patient.gender " +
                "FROM prescription " +
                "INNER JOIN patient ON prescription.patient_id = patient.id " +
                "WHERE prescription.id = ?";
        return jdbcTemplate.query(sql, new PrescriptionRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public int addPrescription(Prescription prescription) {
        String sql = "INSERT INTO prescription(patient_id,date,diagnosis,processed) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql,
                prescription.getPatientId(),
                prescription.getDate(),
                prescription.getDiagnosis(),
                prescription.isProcessed());
    }
}
