package com.hashini.medicare.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "unit_price")
    private float unitPrice;
    @Column(name = "units")
    private int units;

    public Medicine(String name, float unitPrice, int units) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.units = units;
    }

    public Medicine() {
    }

    @OneToMany(mappedBy = "medicine")
    private Set<PrescriptionMedicine> prescriptions;

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
}