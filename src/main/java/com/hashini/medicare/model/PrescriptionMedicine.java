package com.hashini.medicare.model;

import javax.persistence.*;

@Entity
public class PrescriptionMedicine {

    @EmbeddedId
    private PrescriptionMedicineKey id;

    @ManyToOne
    @MapsId("prescriptionId")
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @ManyToOne
    @MapsId("medicineId")
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @Column(name = "dose")
    private String dose;
    @Column(name = "frequency")
    private String frequency;
    @Column(name = "duration")
    private String duration;
    @Column(name = "additional_info")
    private String additionalInfo;
    @Column(name = "quantity")
    private int quantity;

    public PrescriptionMedicine(Prescription prescription,
                                Medicine medicine,
                                String dose,
                                String frequency,
                                String duration,
                                String additionalInfo,
                                int quantity) {
        this.id = new PrescriptionMedicineKey(prescription.getId(), medicine.getId());
        this.prescription = prescription;
        this.medicine = medicine;
        this.dose = dose;
        this.frequency = frequency;
        this.duration = duration;
        this.additionalInfo = additionalInfo;
        this.quantity = quantity;
    }

    public PrescriptionMedicine() {

    }

    public PrescriptionMedicineKey getId() {
        return id;
    }

    public void setId(PrescriptionMedicineKey id) {
        this.id = id;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
