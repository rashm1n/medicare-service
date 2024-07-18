package com.hashini.medicare.mapper;

import com.hashini.medicare.dto.PatientDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class PatientRowMapper implements RowMapper<PatientDTO> {
    @Override
    public PatientDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        PatientDTO patient = new PatientDTO();
        patient.setId(rs.getLong("patient_id"));
        patient.setRegNo(rs.getString("reg_no"));
        patient.setName(rs.getString("name"));
        patient.setAge(rs.getInt("age"));
        patient.setAgeMonths(rs.getInt("age_months"));
        patient.setGender(rs.getString("gender"));
        patient.setNic(rs.getString("nic"));
        patient.setAddress(rs.getString("address"));
        patient.setTpNumber(rs.getInt("tp_number"));
        patient.setAllergies(rs.getString("allergies"));
        patient.setCreatedTime(rs.getObject("created_date", OffsetDateTime.class));
        patient.setUpdatedTime(rs.getObject("updated_date", OffsetDateTime.class));
        return patient;
    }
}