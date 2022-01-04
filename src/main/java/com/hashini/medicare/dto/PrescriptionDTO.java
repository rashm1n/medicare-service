package com.hashini.medicare.dto;

import java.util.Date;

public class PrescriptionDTO {

    private long patientId;
    private Date date;

    public PrescriptionDTO(long patientId, Date date) {
        this.patientId = patientId;
        this.date = date;
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
}
