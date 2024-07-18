package com.hashini.medicare.dto;

public class PrescriptionMedicineDTO {

    private MedicineDTO medicine;
    private long id;
    private String dose;
    private int frequency;
    private String frequencyText;
    private int duration;
    private String additionalInfo;
    private float quantity;
    private float price;

    public PrescriptionMedicineDTO(MedicineDTO medicine,
                                   long id,
                                   String dose,
                                   int frequency,
                                   String frequencyText,
                                   int duration,
                                   String additionalInfo,
                                   float quantity,
                                   float price) {
        this.id = id;
        this.medicine = medicine;
        this.dose = dose;
        this.frequency = frequency;
        this.frequencyText = frequencyText;
        this.duration = duration;
        this.additionalInfo = additionalInfo;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MedicineDTO getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicineDTO medicine) {
        this.medicine = medicine;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getFrequencyText() {
        return frequencyText;
    }

    public void setFrequencyText(String frequencyText) {
        this.frequencyText = frequencyText;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
