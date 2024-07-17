package com.hashini.medicare.dto;

import java.util.List;

public class PrescriptionUpdateDTO {
    private String diagnosis;
    private String history;
    private Boolean processed;
    private Float totalPrice;
    private String consultationInfo;
    private Float consultationFee;
    private String investigationInfo;
    private Float investigationFee;
    private List<PrescriptionMedicineUpdateDTO> medicines;

    public PrescriptionUpdateDTO() {
    }

    public PrescriptionUpdateDTO(String diagnosis,
                                 String history,
                                 Boolean processed,
                                 Float totalPrice,
                                 String consultationInfo,
                                 Float consultationFee, String investigationInfo, Float investigationFee,
                                 List<PrescriptionMedicineUpdateDTO> medicines) {
        this.diagnosis = diagnosis;
        this.history = history;
        this.medicines = medicines;
        this.processed = processed;
        this.totalPrice = totalPrice;
        this.consultationInfo = consultationInfo;
        this.consultationFee = consultationFee;
        this.investigationInfo = investigationInfo;
        this.investigationFee = investigationFee;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
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

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getConsultationInfo() {
        return consultationInfo;
    }

    public void setConsultationInfo(String consultationInfo) {
        this.consultationInfo = consultationInfo;
    }

    public Float getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Float consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getInvestigationInfo() {
        return investigationInfo;
    }

    public void setInvestigationInfo(String investigationInfo) {
        this.investigationInfo = investigationInfo;
    }

    public Float getInvestigationFee() {
        return investigationFee;
    }

    public void setInvestigationFee(Float investigationFee) {
        this.investigationFee = investigationFee;
    }

    public List<PrescriptionMedicineUpdateDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<PrescriptionMedicineUpdateDTO> medicines) {
        this.medicines = medicines;
    }
}