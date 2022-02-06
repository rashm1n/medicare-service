package com.hashini.medicare.service;

import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.model.Medicine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    public List<MedicineDTO> getAllMedicines(Optional<String> medicineName) {
        throw new UnsupportedOperationException("not implemented");
    }

    public MedicineDTO getMedicine(long id) throws NotFoundException {
        throw new UnsupportedOperationException("not implemented");
    }

    public Medicine addMedicine(MedicineDTO newMedicine) throws NotFoundException {
        throw new UnsupportedOperationException("not implemented");
    }

    public Medicine updateMedicine(MedicineDTO newMedicine,
                                   long id) throws NotFoundException {
        throw new UnsupportedOperationException("not implemented");
    }

}