package com.hashini.medicare.dto;

import com.hashini.medicare.model.Patient;

import java.time.OffsetDateTime;
import java.util.List;

public class PrescriptionDTO {

    private long id;
    private Patient patient;
    private OffsetDateTime createdTime;
    private String diagnosis;
    private String history;
    private Boolean processed;
    private float totalPrice;
    private List<PrescriptionMedicineDTO> medicines;

    public PrescriptionDTO(long id, Patient patient, OffsetDateTime createdTime, String diagnosis, String history,
                           Boolean processed, float totalPrice) {
        this.id = id;
        this.patient = patient;
        this.createdTime = createdTime;
        this.diagnosis = diagnosis;
        this.history = history;
        this.processed = processed;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public OffsetDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(OffsetDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<PrescriptionMedicineDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<PrescriptionMedicineDTO> medicines) {
        this.medicines = medicines;
    }
}
