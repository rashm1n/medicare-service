package com.hashini.medicare.model;

public class Medicine {

    private long id;
    private String name;
    private float unitPrice;
    private int units;

    public Medicine(String name, float unitPrice, int units) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.units = units;
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
}