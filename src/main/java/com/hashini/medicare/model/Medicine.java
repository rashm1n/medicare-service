package com.hashini.medicare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "unit_price")
    private float unitPrice;
    @Column(name = "units")
    private int units;

    @ManyToOne
    @JoinColumn(name = "medicinetype_id", nullable = false)
    private MedicineType medicineType;

    @JsonIgnore
    @OneToMany(mappedBy = "medicine")
    private Set<PrescriptionMedicine> prescriptions;

    public Medicine(String name, float unitPrice, int units, MedicineType medicineType) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.units = units;
        this.medicineType = medicineType;
    }

    public Medicine() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public Set<PrescriptionMedicine> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<PrescriptionMedicine> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public MedicineType getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(MedicineType medicineType) {
        this.medicineType = medicineType;
    }
}