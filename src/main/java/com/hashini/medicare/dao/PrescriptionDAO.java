package com.hashini.medicare.dao;

import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.model.Prescription;

import java.util.List;
import java.util.Optional;

public interface PrescriptionDAO {

    List<PrescriptionDTO> selectPrescriptions();

    List<PrescriptionDTO> selectPrescriptionsByProcessed(boolean processed);

    Optional<PrescriptionDTO> selectPrescriptionById(long id);

    long addPrescription(Prescription prescription);
}
