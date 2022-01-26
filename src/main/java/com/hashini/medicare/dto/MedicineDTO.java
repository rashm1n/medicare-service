package com.hashini.medicare.dto;

public class MedicineDTO {

    private long id;
    private String name;
    private float unitPrice;
    private int units;
    private String type;

    public MedicineDTO(long id, String name, float unitPrice, int units, String type) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.units = units;
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

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}