package com.hashini.medicare.mapper;

import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.model.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionMapper implements RowMapper<PrescriptionDTO> {

    @Override
    public PrescriptionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PrescriptionDTO(
                rs.getInt("prescriptionId"),
                new Patient(
                        rs.getInt("patientId"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender")
                ),
                rs.getDate("date"),
                rs.getString("diagnosis")
        );
    }
}