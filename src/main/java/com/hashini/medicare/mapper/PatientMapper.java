package com.hashini.medicare.mapper;

import com.hashini.medicare.model.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientMapper implements RowMapper<Patient> {

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Patient(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("gender")
        );
    }
}
