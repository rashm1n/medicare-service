package com.hashini.medicare.dto;

import java.util.List;

public class PrescriptionCreationDTO {

    private long patientId;
    private String diagnosis;
    private String history;
    private List<PrescriptionMedicineCreationDTO> medicines;

    public PrescriptionCreationDTO() {
    }

    public PrescriptionCreationDTO(long patientId,
                                   String diagnosis,
                                   String history,
                                   List<PrescriptionMedicineCreationDTO> medicines) {
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.history = history;
        this.medicines = medicines;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public List<PrescriptionMedicineCreationDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<PrescriptionMedicineCreationDTO> medicines) {
        this.medicines = medicines;
    }
}
