package com.hashini.medicare.dto;

import com.hashini.medicare.model.Inventory;

public class InventoryDTO {

    private String name;
    private float unitPrice;
    private int units;

    public InventoryDTO(String name, float unitPrice, int units) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.units = units;
    }

    public InventoryDTO(Inventory inventory){
        this.name = inventory.getMedicine().getName();
        this.unitPrice = inventory.getMedicine().getUnitPrice();
        this.units = inventory.getUnits();
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