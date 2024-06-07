package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.PrescriptionMedicineDAO;
import com.hashini.medicare.model.PrescriptionMedicine;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PrescriptionMedicineImpl implements PrescriptionMedicineDAO {

    private final JdbcTemplate jdbcTemplate;

    public PrescriptionMedicineImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addPrescriptionMedicine(PrescriptionMedicine prescriptionMedicine) {
        String sql = "INSERT INTO prescription_medicine(prescription_id, medicine_id, dose, duration, " +
                "frequency, frequency_text, quantity, additional_info) VALUES (?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                prescriptionMedicine.getPrescription_id(),
                prescriptionMedicine.getMedicine_id(),
                prescriptionMedicine.getDose(),
                prescriptionMedicine.getDuration(),
                prescriptionMedicine.getFrequency(),
                prescriptionMedicine.getFrequencyText(),
                prescriptionMedicine.getQuantity(),
                prescriptionMedicine.getAdditionalInfo());
    }
}