package com.hashini.medicare.service;

import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.mapper.MedicineMapper;
import com.hashini.medicare.model.Medicine;
import com.hashini.medicare.model.MedicineType;
import com.hashini.medicare.repository.MedicineRepository;
import com.hashini.medicare.repository.MedicineTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;
    private final MedicineTypeRepository medicineTypeRepository;

    public MedicineService(MedicineRepository medicineRepository,
                           MedicineTypeRepository medicineTypeRepository,
                           MedicineMapper medicineMapper) {
        this.medicineRepository = medicineRepository;
        this.medicineTypeRepository = medicineTypeRepository;
        this.medicineMapper = medicineMapper;
    }

    public List<MedicineDTO> getAllMedicines(Optional<String> medicineName) {
        return medicineName.map(s -> medicineRepository.findByNameIgnoreCaseStartsWith(s)
                .stream().map(medicineMapper::toMedicineDTO).collect(Collectors.toList())
        ).orElseGet(() -> medicineRepository.findAll().stream().map(medicineMapper::toMedicineDTO)
                .collect(Collectors.toList()));
    }

    public MedicineDTO getMedicine(long id) throws NotFoundException {
        return medicineMapper.toMedicineDTO(medicineRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Medicine id = " + id + " not found")));
    }

    public Medicine addMedicine(MedicineDTO newMedicine) throws NotFoundException {
        MedicineType type = medicineTypeRepository.findByName(newMedicine.getType()).orElseThrow(() ->
                new NotFoundException("Medicine Type =" + newMedicine.getType() + " is not found"));
        return medicineRepository.save(medicineMapper.toMedicine(newMedicine, type));
    }

    public Medicine updateMedicine(MedicineDTO newMedicine,
                                   long id) throws NotFoundException {
        MedicineType type = medicineTypeRepository.findByName(newMedicine.getType()).orElseThrow(() ->
                new NotFoundException("Medicine Type =" + newMedicine.getType() + " is not found"));
        return medicineRepository.findById(id)
                .map(medicine -> {
                    medicine.setName(newMedicine.getName());
                    medicine.setUnits(newMedicine.getUnits());
                    medicine.setUnitPrice(newMedicine.getUnitPrice());
                    medicine.setMedicineType(type);
                    return medicineRepository.save(medicine);
                })
                .orElseGet(() -> {
                    newMedicine.setId(id);
                    return medicineRepository.save(medicineMapper.toMedicine(newMedicine, type));
                });
    }
}