package com.hashini.medicare.dao;

import com.hashini.medicare.model.MedicineType;

import java.util.Optional;

public interface MedicineTypeDAO {

    Optional<MedicineType> selectMedicineTypeByName(String name);

}