package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.PrescriptionMedicineDAO;
import com.hashini.medicare.dto.MedicineQuantityDTO;
import com.hashini.medicare.dto.PrescriptionMedicineCreationDTO;
import com.hashini.medicare.dto.PrescriptionMedicineUpdateDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PrescriptionMedicineImpl implements PrescriptionMedicineDAO {

    private final JdbcTemplate jdbcTemplate;

    public PrescriptionMedicineImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MedicineQuantityDTO> findByPrescriptionId(long prescriptionId) {
        String sql = "SELECT medicine_id, quantity FROM prescription_medicines WHERE prescription_id = ?";
        return jdbcTemplate.query(sql, new Object[]{prescriptionId}, (rs, rowNum) ->
                new MedicineQuantityDTO(rs.getInt("medicine_id"), rs.getFloat("quantity"))
        );
    }

    @Override
    public void addPrescriptionMedicines(Long prescriptionId, List<PrescriptionMedicineCreationDTO> medicines) {
        String insertSql = "INSERT INTO prescription_medicines(prescription_id, medicine_id, dose, duration, " +
                "frequency, frequency_text, quantity, additional_info, price) VALUES (?,?,?,?,?,?,?,?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (PrescriptionMedicineCreationDTO medicine : medicines) {
            batchArgs.add(new Object[]{prescriptionId, medicine.getMedicineId(), medicine.getDose(),
                    medicine.getDuration(), medicine.getFrequency(), medicine.getFrequencyText(),
                    medicine.getQuantity(), medicine.getAdditionalInfo(), medicine.getPrice()});
        }
        jdbcTemplate.batchUpdate(insertSql, batchArgs);
    }

    @Override
    public void updatePrescriptionMedicines(Long prescriptionId, List<PrescriptionMedicineUpdateDTO> medicines) {
        // Remove existing medicines
        String deleteSql = "DELETE FROM prescription_medicines WHERE prescription_id = ?";
        jdbcTemplate.update(deleteSql, prescriptionId);

        // Add updated medicines
        String insertSql = "INSERT INTO prescription_medicines(prescription_id, medicine_id, dose, duration, " +
                "frequency, frequency_text, quantity, additional_info, price) VALUES (?,?,?,?,?,?,?,?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (PrescriptionMedicineUpdateDTO medicine : medicines) {
            batchArgs.add(new Object[]{prescriptionId, medicine.getMedicineId(), medicine.getDose(),
                    medicine.getDuration(), medicine.getFrequency(), medicine.getFrequencyText(),
                    medicine.getQuantity(), medicine.getAdditionalInfo(), medicine.getPrice()});
        }
        jdbcTemplate.batchUpdate(insertSql, batchArgs);
    }
}