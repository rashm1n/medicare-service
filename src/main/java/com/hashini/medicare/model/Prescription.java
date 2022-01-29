package com.hashini.medicare.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToMany(mappedBy = "prescription")
    private Set<PrescriptionMedicine> medicines;

    @Column(name = "date")
    private Date date;

    @Column(name = "processed")
    private boolean processed;

    @Column(name = "diagnosis")
    private String diagnosis;

    public Prescription(Patient patient, Date date, String diagnosis) {
        this.patient = patient;
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

    public Date getDate() {
        return date;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
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

    public Set<PrescriptionMedicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<PrescriptionMedicine> medicines) {
        this.medicines = medicines;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}