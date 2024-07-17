package com.hashini.medicare.dto;

import java.util.List;

public class PrescriptionCreationDTO {

    private long patientId;
    private String diagnosis;
    private String history;
    private float totalPrice;
    private String consultationInfo;
    private float consultationFee;
    private String investigationInfo;
    private float investigationFee;
    private List<PrescriptionMedicineCreationDTO> medicines;

    public PrescriptionCreationDTO() {
    }

    public PrescriptionCreationDTO(long patientId,
                                   String diagnosis,
                                   String history,
                                   float totalPrice, String consultationInfo,
                                   float consultationFee, String investigationInfo, float investigationFee,
                                   List<PrescriptionMedicineCreationDTO> medicines) {
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.history = history;
        this.medicines = medicines;
        this.totalPrice = totalPrice;
        this.consultationInfo = consultationInfo;
        this.consultationFee = consultationFee;
        this.investigationInfo = investigationInfo;
        this.investigationFee = investigationFee;
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

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getConsultationInfo() {
        return consultationInfo;
    }

    public void setConsultationInfo(String consultationInfo) {
        this.consultationInfo = consultationInfo;
    }

    public float getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(float consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getInvestigationInfo() {
        return investigationInfo;
    }

    public void setInvestigationInfo(String investigationInfo) {
        this.investigationInfo = investigationInfo;
    }

    public float getInvestigationFee() {
        return investigationFee;
    }

    public void setInvestigationFee(float investigationFee) {
        this.investigationFee = investigationFee;
    }

    public List<PrescriptionMedicineCreationDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<PrescriptionMedicineCreationDTO> medicines) {
        this.medicines = medicines;
    }
}
