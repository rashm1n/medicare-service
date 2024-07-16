package com.hashini.medicare.model;

public class Prescription {

    private long id;
    private long patientId;
    private String diagnosis;
    private String history;
    private boolean processed;
    private float totalPrice;

    public Prescription(long patientId, String diagnosis, String history, float totalPrice) {
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.history = history;
        this.processed = false;
        this.totalPrice = totalPrice;
    }

    public Prescription() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}