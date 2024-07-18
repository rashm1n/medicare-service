package com.hashini.medicare.dto;

public class MedicineQuantityDTO {

    private int medicineId;
    private float quantity;

    public MedicineQuantityDTO(int medicineId, float quantity) {
        this.medicineId = medicineId;
        this.quantity = quantity;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
