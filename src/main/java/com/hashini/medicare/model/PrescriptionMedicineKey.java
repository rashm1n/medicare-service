package com.hashini.medicare.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PrescriptionMedicineKey implements Serializable {

    @Column(name = "prescription_id")
    long prescriptionId;

    @Column(name = "medicine_id")
    long medicineId;

    public long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(long medicineId) {
        this.medicineId = medicineId;
    }
}
