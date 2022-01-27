package com.hashini.medicare.dto;

import com.hashini.medicare.model.Patient;

import java.util.Date;
import java.util.List;

public class PrescriptionDTO {

    private Patient patient;
    private Date date;
    private List<PrescriptionMedicineDTO> medicines;

    public PrescriptionDTO(Patient patient, Date date, List<PrescriptionMedicineDTO> medicines) {
        this.patient = patient;
        this.date = date;
        this.medicines = medicines;
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

    public List<PrescriptionMedicineDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<PrescriptionMedicineDTO> medicines) {
        this.medicines = medicines;
    }
}
