package com.hashini.medicare.dao;

import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.model.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineDAO {

    List<MedicineDTO> selectMedicinesByLowInventory(Boolean lowInventory);

    List<MedicineDTO> selectMedicinesByNameAndLowInventory(String medicineName,
                                                           Boolean lowInventory);

    int addMedicine(Medicine medicine);

    int updateMedicine(Medicine medicine, long id);

    Optional<MedicineDTO> selectMedicineById(long id);

    Optional<MedicineDTO> selectMedicineByName(String name);

    int deleteMedicine(long id);

    int updateUnits(long id,
                    int decrementQuantity);
}
