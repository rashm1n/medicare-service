package com.hashini.medicare.service;

import com.hashini.medicare.dao.MedicineDAO;
import com.hashini.medicare.dao.MedicineTypeDAO;
import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.mapper.MedicineMapper;
import com.hashini.medicare.model.MedicineType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    private final MedicineDAO medicineDAO;
    private final MedicineTypeDAO medicineTypeDAO;

    public MedicineService(MedicineDAO medicineDAO,
                           MedicineTypeDAO medicineTypeDAO) {
        this.medicineDAO = medicineDAO;
        this.medicineTypeDAO = medicineTypeDAO;
    }

    public List<MedicineDTO> getAllMedicines(Optional<String> medicineName,
                                             Boolean lowInventory) {
        return medicineName.map(name -> medicineDAO.selectMedicinesByNameAndLowInventory(name, lowInventory)).
                orElseGet(() -> medicineDAO.selectMedicinesByLowInventory(lowInventory));
    }

    public MedicineDTO getMedicine(long id) throws NotFoundException {
        return medicineDAO.selectMedicineById(id)
                .orElseThrow(() -> new NotFoundException("Medicine id = " + id + " not found"));
    }

    public int addMedicine(MedicineDTO newMedicine) throws NotFoundException {
        return medicineTypeDAO.selectMedicineTypeByName(newMedicine.getType())
                .map(medicineType -> medicineDAO.addMedicine(new MedicineMapper().toMedicine(newMedicine, medicineType)))
                .orElseThrow(() -> new NotFoundException("Medicine type = " + newMedicine.getType() + " is not found"));
    }

    public int updateMedicine(MedicineDTO newMedicine,
                              long id) throws NotFoundException {
        MedicineType medicineType = medicineTypeDAO.selectMedicineTypeByName(newMedicine.getType())
                .orElseThrow(() -> new NotFoundException("Medicine Type = " + newMedicine.getType() + " is not found"));
        return medicineDAO.selectMedicineById(id)
                .map(medicineDTO -> medicineDAO.updateMedicine(new MedicineMapper().toMedicine(newMedicine, medicineType), id))
                .orElseGet(() -> {
                    newMedicine.setId(id);
                    return medicineDAO.addMedicine(new MedicineMapper().toMedicine(newMedicine, medicineType));
                });
    }

    public int deleteMedicine(long id) {
        return medicineDAO.selectMedicineById(id)
                .map(medicine -> medicineDAO.deleteMedicine(id))
                .orElseThrow(() -> new NotFoundException("Medicine id = " + id + " not found"));
    }
}