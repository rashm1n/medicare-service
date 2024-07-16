package com.hashini.medicare.dao;

import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.model.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineDAO {

    List<MedicineDTO> selectMedicinesByLowInventory(Boolean lowInventory,
                                                    int cityId);

    List<MedicineDTO> selectMedicinesByNameAndLowInventory(String medicineName,
                                                           Boolean lowInventory,
                                                           int cityId);

    int addMedicine(Medicine medicine,
                    int cityId);

    int updateMedicine(Medicine medicine, long id);

    Optional<MedicineDTO> selectMedicineById(long id, int cityId);

    Optional<MedicineDTO> selectMedicineByName(String name);

    int deleteMedicine(long id);

    void updateUnits(long id,
                     int quantityDifference);
}
