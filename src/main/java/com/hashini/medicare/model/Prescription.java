package com.hashini.medicare.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @OneToMany(mappedBy = "prescription")
    private Set<PrescriptionMedicine> medicines;

    @Column(name = "date")
    private Date date;

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

    public Set<PrescriptionMedicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<PrescriptionMedicine> medicines) {
        this.medicines = medicines;
    }
}