package com.hashini.medicare.service;

import com.hashini.medicare.dao.PatientDAO;
import com.hashini.medicare.dao.PrescriptionDAO;
import com.hashini.medicare.dto.AnalyticsDTO;
import com.hashini.medicare.dto.PrescriptionAnalyticsDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnalyticsService {

    private final PatientDAO patientDAO;

    private final PrescriptionDAO prescriptionDAO;

    public AnalyticsService(PatientDAO patientDAO, PrescriptionDAO prescriptionDAO) {
        this.patientDAO = patientDAO;
        this.prescriptionDAO = prescriptionDAO;
    }

    public AnalyticsDTO getAnalytics(LocalDateTime startDate,
                                     LocalDateTime endDate,
                                     int cityId) {
        int patientsCount = patientDAO.getCount(startDate, endDate, cityId);
        PrescriptionAnalyticsDTO prescriptionAnalyticsDTO = prescriptionDAO.getTotalRevenueAndCount(startDate,
                endDate, cityId);
        return new AnalyticsDTO(patientsCount, prescriptionAnalyticsDTO.getTotalCount(),
                prescriptionAnalyticsDTO.getTotalRevenue());
    }
}