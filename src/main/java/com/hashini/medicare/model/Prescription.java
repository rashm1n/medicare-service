package com.hashini.medicare.model;

import java.util.Date;

public class Prescription {

    private long id;
    private long patientId;
    private Date date;
    private String diagnosis;
    private boolean processed;

    public Prescription(long patientId, Date date, String diagnosis) {
        this.patientId = patientId;
        this.date = date;
        this.diagnosis = diagnosis;
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

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}