package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.MedicineTypeDAO;
import com.hashini.medicare.model.MedicineType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MedicineTypeDAOImpl implements MedicineTypeDAO {

    private final JdbcTemplate jdbcTemplate;

    public MedicineTypeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<MedicineType> selectMedicineTypeByName(String name) {
        String sql = "SELECT * FROM medicinetypes WHERE type = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new MedicineType(
                        rs.getInt("medicinetype_id"),
                        rs.getString("type")), name)
                .stream()
                .findFirst();
    }
}