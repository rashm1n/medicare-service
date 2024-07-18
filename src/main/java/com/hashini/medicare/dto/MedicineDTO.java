package com.hashini.medicare.dto;

public class MedicineDTO {

    private long id;
    private String name;
    private float unitPrice;
    private float units;
    private int minimumUnits;
    private String type;

    public MedicineDTO() {
    }

    public MedicineDTO(long id, String name, float unitPrice, float units, int minimumUnits, String type) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.units = units;
        this.minimumUnits = minimumUnits;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getMinimumUnits() {
        return minimumUnits;
    }

    public void setMinimumUnits(int minimumUnits) {
        this.minimumUnits = minimumUnits;
    }

    public float getUnits() {
        return units;
    }

    public void setUnits(float units) {
        this.units = units;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}