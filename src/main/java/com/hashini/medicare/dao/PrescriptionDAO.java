package com.hashini.medicare.dao;

import com.hashini.medicare.dto.PrescriptionAnalyticsDTO;
import com.hashini.medicare.dto.PrescriptionCreationDTO;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.dto.PrescriptionUpdateDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PrescriptionDAO {
    List<PrescriptionDTO> findAllPrescriptions(Optional<Boolean> processed,
                                               Optional<String> searchTerm,
                                               LocalDateTime startDate,
                                               LocalDateTime endDate,
                                               int cityId);

    Optional<PrescriptionDTO> selectPrescriptionById(long id, int cityId);

    long addPrescription(PrescriptionCreationDTO prescription);

    long updatePrescription(PrescriptionUpdateDTO prescription, long id);

    PrescriptionAnalyticsDTO getTotalRevenueAndCount(LocalDateTime startDate,
                                                     LocalDateTime endDate,
                                                     int cityId);

}