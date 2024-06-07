package com.hashini.medicare.model;

public class Prescription {

    private long id;
    private long patientId;
    private String diagnosis;
    private String history;
    private boolean processed;

    public Prescription(long patientId, String diagnosis, String history) {
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.history = history;
        this.processed = false;
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
}