package com.hashini.medicare.dto;

public class AnalyticsDTO {

    private int totalPatients;

    private int totalPrescriptions;

    private float totalPrescriptionRevenue;


    public AnalyticsDTO(int totalPatients, int totalPrescriptions, float totalPrescriptionRevenue) {
        this.totalPatients = totalPatients;
        this.totalPrescriptions = totalPrescriptions;
        this.totalPrescriptionRevenue = totalPrescriptionRevenue;
    }

    public int getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(int totalPatients) {
        this.totalPatients = totalPatients;
    }

    public int getTotalPrescriptions() {
        return totalPrescriptions;
    }

    public void setTotalPrescriptions(int totalPrescriptions) {
        this.totalPrescriptions = totalPrescriptions;
    }

    public float getTotalPrescriptionRevenue() {
        return totalPrescriptionRevenue;
    }

    public void setTotalPrescriptionRevenue(float totalPrescriptionRevenue) {
        this.totalPrescriptionRevenue = totalPrescriptionRevenue;
    }
}