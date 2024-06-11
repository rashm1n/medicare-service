package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.PrescriptionDAO;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.mapper.PrescriptionMapper;
import com.hashini.medicare.model.Prescription;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
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
    public List<PrescriptionDTO> findAllPrescriptions(Optional<Boolean> processed,
                                                      Optional<String> searchTerm,
                                                      LocalDateTime startDate,
                                                      LocalDateTime endDate) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * " +
                "FROM prescription " +
                "INNER JOIN patient ON prescription.patient_id = patient.patient_id " +
                "LEFT JOIN prescription_medicine pm on prescription.prescription_id = pm.prescription_id " +
                "LEFT JOIN medicine m on pm.medicine_id = m.medicine_id " +
                "LEFT JOIN medicinetype m2 on m2.medicinetype_id = m.medicinetype_id WHERE ");
        boolean firstCondition = true;

        if (processed.isPresent()) {
            sql.append("prescription.processed = ?");
            params.add(processed.get());
            firstCondition = false;
        }

        if (searchTerm.isPresent()) {
            if (!firstCondition) sql.append(" AND ");
            sql.append("LOWER(patient.patient_name) LIKE ? ");
            params.add("%" + searchTerm.get().toLowerCase() + "%");
            firstCondition = false;
        }

        if (startDate != null && endDate != null) {
            if (!firstCondition) sql.append(" AND ");
            sql.append("prescription.created_date BETWEEN ? AND ?");
            params.add(startDate);
            params.add(endDate);
        }
        return new ArrayList<>(Objects.requireNonNull(jdbcTemplate.query(sql.toString(), new PrescriptionMapper(),
                params.toArray())).values());
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
        String sql = "INSERT INTO prescription (patient_id,diagnosis,history,processed) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"prescription_id"});
            ps.setLong(1, prescription.getPatientId());
            ps.setString(2, prescription.getDiagnosis());
            ps.setString(3, prescription.getHistory());
            ps.setBoolean(4, prescription.isProcessed());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public long updatePrescription(Prescription prescription, long id) {
        String sql = "UPDATE prescription SET patient_id = ?, diagnosis = ? ,history = ?, processed = ? " +
                "WHERE prescription_id = ?";
        return jdbcTemplate.update(sql, prescription.getPatientId(), prescription.getDiagnosis(),
                prescription.getHistory(), prescription.isProcessed(), id);
    }
}
