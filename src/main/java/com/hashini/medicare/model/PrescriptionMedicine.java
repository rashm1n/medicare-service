package com.hashini.medicare.model;

public class PrescriptionMedicine {

    private long prescription_id;
    private long medicine_id;
    private String dose;
    private int duration;
    private int frequency;
    private String frequencyText;
    private int quantity;
    private String additionalInfo;

    public PrescriptionMedicine(long prescription_id,
                                long medicine_id,
                                String dose,
                                int duration,
                                int frequency,
                                String frequencyText,
                                int quantity,
                                String additionalInfo) {
        this.prescription_id = prescription_id;
        this.medicine_id = medicine_id;
        this.dose = dose;
        this.duration = duration;
        this.frequency = frequency;
        this.frequencyText = frequencyText;
        this.quantity = quantity;
        this.additionalInfo = additionalInfo;
    }

    public long getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(long prescription_id) {
        this.prescription_id = prescription_id;
    }

    public long getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(long medicine_id) {
        this.medicine_id = medicine_id;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getFrequencyText() {
        return frequencyText;
    }

    public void setFrequencyText(String frequencyText) {
        this.frequencyText = frequencyText;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
