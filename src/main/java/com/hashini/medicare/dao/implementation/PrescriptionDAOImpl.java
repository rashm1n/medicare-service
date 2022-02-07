package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.PrescriptionDAO;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.mapper.PrescriptionMapper;
import com.hashini.medicare.model.Prescription;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PrescriptionDAOImpl implements PrescriptionDAO {

    private final JdbcTemplate jdbcTemplate;

    public PrescriptionDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PrescriptionDTO> selectPrescriptions() {
        String sql = "SELECT *" +
                "FROM prescription " +
                "INNER JOIN patient p ON prescription.patient_id = p.patient_id " +
                "LEFT JOIN prescription_medicine pm on prescription.prescription_id = pm.prescription_id " +
                "LEFT JOIN medicine m on pm.medicine_id = m.medicine_id " +
                "LEFT JOIN medicinetype m2 on m2.medicinetype_id = m.medicinetype_id";
        return new ArrayList<>((Objects.requireNonNull(jdbcTemplate.query(sql, new PrescriptionMapper()))).values());
    }

    @Override
    public List<PrescriptionDTO> selectPrescriptionsByProcessed(boolean processed) {
        String sql = "SELECT *" +
                "FROM prescription " +
                "INNER JOIN patient ON prescription.patient_id = patient.patient_id " +
                "LEFT JOIN prescription_medicine pm on prescription.prescription_id = pm.prescription_id " +
                "LEFT JOIN medicine m on pm.medicine_id = m.medicine_id " +
                "LEFT JOIN medicinetype m2 on m2.medicinetype_id = m.medicinetype_id " +
                "WHERE prescription.processed = ?";
        return new ArrayList<>(Objects.requireNonNull(jdbcTemplate.query(sql, new PrescriptionMapper(), processed)).values());
    }

    @Override
    public Optional<PrescriptionDTO> selectPrescriptionById(long id) {
        String sql = "SELECT *" +
                "FROM prescription " +
                "INNER JOIN patient ON prescription.patient_id = patient.patient_id " +
                "LEFT JOIN prescription_medicine pm on prescription.prescription_id = pm.prescription_id " +
                "LEFT JOIN medicine m on pm.medicine_id = m.medicine_id " +
                "LEFT JOIN medicinetype m2 on m2.medicinetype_id = m.medicinetype_id " +
                "WHERE prescription.prescription_id = ?";
        return new ArrayList<>(Objects.requireNonNull(jdbcTemplate.query(sql, new PrescriptionMapper(), id)).values())
                .stream()
                .findFirst();
    }

    @Override
    public long addPrescription(Prescription prescription) {
        String sql = "INSERT INTO prescription (patient_id,date,diagnosis,processed) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"prescription_id"});
            ps.setLong(1, prescription.getPatientId());
            ps.setDate(2, new Date(prescription.getDate().getTime()));
            ps.setString(3, prescription.getDiagnosis());
            ps.setBoolean(4, prescription.isProcessed());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
