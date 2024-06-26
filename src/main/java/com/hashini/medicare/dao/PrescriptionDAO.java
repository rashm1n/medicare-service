package com.hashini.medicare.dao;

import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.model.Prescription;

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

    long addPrescription(Prescription prescription);

    long updatePrescription(Prescription prescription, long id);
}
