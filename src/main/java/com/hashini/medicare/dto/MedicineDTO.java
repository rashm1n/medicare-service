package com.hashini.medicare.dto;

public class MedicineDTO {

    private String name;
    private float unitPrice;
    private int units;

    public MedicineDTO(String name, float unitPrice, int units) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}