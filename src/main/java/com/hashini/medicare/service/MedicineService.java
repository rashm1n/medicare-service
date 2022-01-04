package com.hashini.medicare.service;

import com.hashini.medicare.model.Medicine;
import com.hashini.medicare.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public Medicine updateMedicine(Medicine newMedicine, long id) {
       return medicineRepository.findById(id)
                .map(medicine -> {
                    medicine.setName(newMedicine.getName());
                    medicine.setUnitPrice(newMedicine.getUnitPrice());
                    return medicineRepository.save(medicine);
                })
                .orElseGet(() -> {
                    newMedicine.setId(id);
                    return medicineRepository.save(newMedicine);
                });
    }
}