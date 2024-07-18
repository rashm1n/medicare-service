package com.hashini.medicare.mapper;

import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.model.Medicine;
import com.hashini.medicare.model.MedicineType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineMapper implements RowMapper<MedicineDTO> {

    @Override
    public MedicineDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new MedicineDTO(
                rs.getInt("medicine_id"),
                rs.getString("medicine_name"),
                rs.getFloat("unit_price"),
                rs.getFloat("units"),
                rs.getInt("minimum_units"),
                rs.getString("type")
        );
    }

    public Medicine toMedicine(MedicineDTO medicineDTO, MedicineType type) {
        return new Medicine(
                medicineDTO.getId(),
                medicineDTO.getName(),
                medicineDTO.getUnitPrice(),
                medicineDTO.getUnits(),
                medicineDTO.getMinimumUnits(),
                type.getId());
    }
}