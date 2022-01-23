package com.hashini.medicare.dto;

public class PrescriptionMedicineDTO {

    private String medicineName;
    private String dose;
    private String frequency;
    private String duration;
    private String additionalInfo;

    public PrescriptionMedicineDTO(String medicineName,
                                   String dose,
                                   String frequency,
                                   String duration,
                                   String additionalInfo) {
        this.medicineName = medicineName;
        this.dose = dose;
        this.frequency = frequency;
        this.duration = duration;
        this.additionalInfo = additionalInfo;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
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
}
