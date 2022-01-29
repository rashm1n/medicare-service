package com.hashini.medicare.dto;

import java.util.Date;
import java.util.List;

public class PrescriptionCreationDTO {

    private long patientId;
    private Date date;
    private String diagnosis;
    private List<PrescriptionMedicineCreationDTO> medicines;

    public PrescriptionCreationDTO(long patientId,
                                   Date date,
                                   String diagnosis,
                                   List<PrescriptionMedicineCreationDTO> medicines) {
        this.patientId = patientId;
        this.date = date;
        this.diagnosis = diagnosis;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<PrescriptionMedicineCreationDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<PrescriptionMedicineCreationDTO> medicines) {
        this.medicines = medicines;
    }
}
