package com.hashini.medicare.mapper;

import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.dto.PrescriptionMedicineDTO;
import com.hashini.medicare.model.Patient;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrescriptionMapper implements ResultSetExtractor<Map<Long, PrescriptionDTO>> {

    @Override
    public Map<Long, PrescriptionDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, PrescriptionDTO> prescriptionsById = new HashMap<>();
        while (rs.next()) {
            long prescriptionId = rs.getLong("prescription_id");
            PrescriptionDTO prescription = prescriptionsById.get(prescriptionId);
            if (prescriptionsById.get(prescriptionId) == null) {
                prescription = new PrescriptionDTO(
                        rs.getLong("prescription_id"),
                        new Patient(
                                rs.getLong("patient_id"),
                                rs.getString("patient_code"),
                                rs.getString("patient_name"),
                                rs.getInt("age"),
                                rs.getString("gender"),
                                rs.getString("nic"),
                                rs.getInt("tp_number"),
                                rs.getString("address"),
                                rs.getString("allergies")
                        ),
                        rs.getObject("created_date", OffsetDateTime.class),
                        rs.getString("diagnosis"),
                        rs.getString("history"),
                        rs.getBoolean("processed"));
                prescriptionsById.put(prescription.getId(), prescription);
            }
            List<PrescriptionMedicineDTO> prescriptionMedicineDTOList = prescription.getMedicines();
            if (prescriptionMedicineDTOList == null) {
                prescriptionMedicineDTOList = new ArrayList<>();
                prescription.setMedicines(prescriptionMedicineDTOList);
            }
            PrescriptionMedicineDTO prescriptionMedicineDTO = new PrescriptionMedicineDTO(
                    new MedicineDTO(rs.getLong("medicine_id"),
                            rs.getString("medicine_name"),
                            rs.getFloat("unit_price"),
                            rs.getInt("units"),
                            rs.getInt("minimum_units"),
                            rs.getString("type")),
                    rs.getString("dose"),
                    rs.getInt("frequency"),
                    rs.getString("frequency_text"),
                    rs.getInt("duration"),
                    rs.getString("additional_info"),
                    rs.getInt("quantity")
            );
            prescriptionMedicineDTOList.add(prescriptionMedicineDTO);
        }
        return prescriptionsById;
    }
}