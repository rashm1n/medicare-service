package com.hashini.medicare.dto;

import java.util.Date;
import java.util.List;

public class PrescriptionDTO {

    private long patientId;
    private Date date;
    private List<PrescriptionMedicineDTO> medicines;

    public PrescriptionDTO(long patientId, Date date, List<PrescriptionMedicineDTO> medicines) {
        this.patientId = patientId;
        this.date = date;
        this.medicines = medicines;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<PrescriptionMedicineDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<PrescriptionMedicineDTO> medicines) {
        this.medicines = medicines;
    }
}
