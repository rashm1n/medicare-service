package com.hashini.medicare.service;

import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.mapper.MedicineMapper;
import com.hashini.medicare.model.Medicine;
import com.hashini.medicare.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;

    public MedicineService(MedicineRepository medicineRepository,
                           MedicineMapper medicineMapper) {
        this.medicineRepository = medicineRepository;
        this.medicineMapper = medicineMapper;
    }

    public List<MedicineDTO> getAllMedicines(Optional<String> medicineName) {
        return medicineName.map(s -> medicineRepository.findByNameIgnoreCaseStartsWith(s)
                .stream().map(medicineMapper::toMedicineDTO).collect(Collectors.toList())
        ).orElseGet(() -> medicineRepository.findAll().stream().map(medicineMapper::toMedicineDTO)
                .collect(Collectors.toList()));
    }

    public MedicineDTO getMedicine(long id) {
        return medicineMapper.toMedicineDTO(medicineRepository.findById(id).get());
    }

    public Medicine addMedicine(MedicineDTO newMedicine) {
        return medicineRepository.save(medicineMapper.toMedicine(newMedicine));
    }

    public Medicine updateMedicine(Medicine newMedicine,
                                   long id) {
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