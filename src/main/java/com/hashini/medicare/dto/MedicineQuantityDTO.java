package com.hashini.medicare.dto;

public class MedicineQuantityDTO {

    private int medicineId;
    private int quantity;

    public MedicineQuantityDTO(int medicineId, int quantity) {
        this.medicineId = medicineId;
        this.quantity = quantity;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
