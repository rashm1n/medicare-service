package com.hashini.medicare.model;

public class Medicine {

    private long id;
    private String name;
    private float unitPrice;
    private float units;
    private int minimumUnits;
    private long medicineTypeId;

    public Medicine(long id, String name, float unitPrice, float units, int minimumUnits, long medicineTypeId) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.units = units;
        this.minimumUnits = minimumUnits;
        this.medicineTypeId = medicineTypeId;
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

    public float getUnits() {
        return units;
    }

    public void setUnits(float units) {
        this.units = units;
    }

    public int getMinimumUnits() {
        return minimumUnits;
    }

    public void setMinimumUnits(int minimumUnits) {
        this.minimumUnits = minimumUnits;
    }

    public long getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(long medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }
}