package com.hashini.medicare.dto;

import com.hashini.medicare.model.Patient;

import java.util.Date;
import java.util.List;

public class PrescriptionDTO {

    private long id;
    private Patient patient;
    private Date date;
    private String diagnosis;
//    private List<PrescriptionMedicineDTO> medicines;

    public PrescriptionDTO(long id, Patient patient, Date date, String diagnosis) {
        this.id = id;
        this.patient = patient;
        this.date = date;
        this.diagnosis = diagnosis;
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
//
//    public List<PrescriptionMedicineDTO> getMedicines() {
//        return medicines;
//    }
//
//    public void setMedicines(List<PrescriptionMedicineDTO> medicines) {
//        this.medicines = medicines;
//    }
}
