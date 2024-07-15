package com.hashini.medicare.dto;

public class PrescriptionAnalyticsDTO {

    private int totalCount;
    private float totalRevenue;

    public PrescriptionAnalyticsDTO(int totalCount, float totalRevenue) {
        this.totalCount = totalCount;
        this.totalRevenue = totalRevenue;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public float getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(float totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}