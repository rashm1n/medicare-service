package com.hashini.medicare.dto;

import java.util.List;

public class PrescriptionUpdateDTO {
    private String diagnosis;
    private String history;
    private Boolean processed;
    private Float totalPrice;
    private List<PrescriptionMedicineUpdateDTO> medicines;

    public PrescriptionUpdateDTO() {
    }

    public PrescriptionUpdateDTO(String diagnosis,
                                 String history,
                                 Boolean processed,
                                 Float totalPrice,
                                 List<PrescriptionMedicineUpdateDTO> medicines) {
        this.diagnosis = diagnosis;
        this.history = history;
        this.medicines = medicines;
        this.processed = processed;
        this.totalPrice = totalPrice;
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

    public List<PrescriptionMedicineUpdateDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<PrescriptionMedicineUpdateDTO> medicines) {
        this.medicines = medicines;
    }
}