/*
package com.hashini.medicare.model;

import javax.persistence.*;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medicine_id", referencedColumnName = "id")
    private Medicine medicine;

    @Column(name = "units")
    private int units;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
*/
