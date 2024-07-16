package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.PrescriptionDAO;
import com.hashini.medicare.dto.PrescriptionAnalyticsDTO;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.dto.PrescriptionUpdateDTO;
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
                                                      LocalDateTime endDate,
                                                      int cityId) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * " +
                "FROM prescriptions pr " +
                "INNER JOIN patients pa ON pr.patient_id = pa.patient_id " +
                "LEFT JOIN prescription_medicines pm on pr.prescription_id = pm.prescription_id " +
                "LEFT JOIN medicines m on pm.medicine_id = m.medicine_id " +
                "LEFT JOIN medicinetypes m2 on m2.medicinetype_id = m.medicinetype_id WHERE pa.city_id = ? ");
        params.add(cityId);

        if (processed.isPresent()) {
            sql.append("AND pr.processed = ?");
            params.add(processed.get());
        }

        if (searchTerm.isPresent()) {
            sql.append(" AND LOWER(pa.name) LIKE ? ");
            params.add("%" + searchTerm.get().toLowerCase() + "%");
        }

        if (startDate != null && endDate != null) {
            sql.append(" AND pr.created_date BETWEEN ? AND ?");
            params.add(startDate);
            params.add(endDate);
        }
        return new ArrayList<>(Objects.requireNonNull(jdbcTemplate.query(sql.toString(), new PrescriptionMapper(),
                params.toArray())).values());
    }

    @Override
    public Optional<PrescriptionDTO> selectPrescriptionById(long id, int cityId) {
        String sql = "SELECT * " +
                "FROM prescriptions pr " +
                "INNER JOIN patients pa ON pr.patient_id = pa.patient_id " +
                "LEFT JOIN prescription_medicines pm on pr.prescription_id = pm.prescription_id " +
                "LEFT JOIN medicines m on pm.medicine_id = m.medicine_id " +
                "LEFT JOIN medicinetypes m2 on m2.medicinetype_id = m.medicinetype_id " +
                "WHERE pr.prescription_id = ? AND pa.city_id = ? ";
        return new ArrayList<>(Objects.requireNonNull(jdbcTemplate.query(sql, new PrescriptionMapper(), id, cityId))
                .values())
                .stream()
                .findFirst();
    }

    @Override
    public long addPrescription(Prescription prescription) {
        String sql = "INSERT INTO prescriptions (patient_id,diagnosis,history,processed,total_price) " +
                "VALUES (?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"prescription_id"});
            ps.setLong(1, prescription.getPatientId());
            ps.setString(2, prescription.getDiagnosis());
            ps.setString(3, prescription.getHistory());
            ps.setBoolean(4, prescription.isProcessed());
            ps.setFloat(5, prescription.getTotalPrice());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public long updatePrescription(PrescriptionUpdateDTO prescription, long id) {
        StringBuilder sql = new StringBuilder("UPDATE prescriptions SET ");
        List<Object> params = new ArrayList<>();
        if (prescription.getDiagnosis() != null) {
            sql.append("diagnosis = ?, ");
            params.add(prescription.getDiagnosis());
        }

        if (prescription.getHistory() != null) {
            sql.append("history = ?, ");
            params.add(prescription.getHistory());
        }

        if (prescription.getTotalPrice() != null) {
            sql.append("total_price = ?, ");
            params.add(prescription.getTotalPrice());
        }

        if (prescription.getProcessed() != null) {
            sql.append("processed = ?, ");
            params.add(prescription.getProcessed());
        }

        if (!params.isEmpty()) {
            sql.setLength(sql.length() - 2);
            sql.append(" WHERE prescription_id = ?");
            params.add(id);
        } else {
            throw new IllegalArgumentException("No fields to update");
        }

        return jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public PrescriptionAnalyticsDTO getTotalRevenueAndCount(LocalDateTime startDate, LocalDateTime endDate, int cityId) {
        String sql = "SELECT SUM(total_price) AS total_price_sum, COUNT(*) as total_prescription_count " +
                "FROM prescriptions pr INNER JOIN patients pa ON pr.patient_id = pa.patient_id " +
                "WHERE pa.city_id = ? AND pr.created_date BETWEEN ? AND ? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{cityId, startDate, endDate}, (rs, rowNum) ->
                new PrescriptionAnalyticsDTO(rs.getInt("total_prescription_count"),
                        rs.getFloat("total_price_sum")));
    }
}
