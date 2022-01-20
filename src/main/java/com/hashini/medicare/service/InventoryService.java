/*
package com.hashini.medicare.service;

import com.hashini.medicare.model.Inventory;
import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.model.Medicine;
import com.hashini.medicare.repository.InventoryRepository;
import com.hashini.medicare.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final MedicineRepository medicineRepository;

    public InventoryService(InventoryRepository inventoryRepository,
                            MedicineRepository medicineRepository) {
        this.inventoryRepository = inventoryRepository;
        this.medicineRepository = medicineRepository;
    }

    public List<MedicineDTO> getAllInventories(Optional<String> medicineName) {
        return medicineName.map(s -> medicineRepository.findByNameIgnoreCaseStartsWith(s)
                .stream().map(medicine -> new MedicineDTO(inventoryRepository.findByMedicine(medicine)))
                .collect(Collectors.toList())
        ).orElseGet(() -> inventoryRepository.findAll().stream().map(MedicineDTO::new).collect(Collectors.toList()));
    }

    public Inventory getInventory(long id) {
        return inventoryRepository.findById(id).get();
    }

    public Inventory addInventory(MedicineDTO newInventory) {
        Inventory inventory = new Inventory();
        return medicineRepository.findByName(newInventory.getName())
                .map(medicine -> {
                    inventory.setMedicine(medicine);
                    inventory.setUnits(newInventory.getUnits());
                    return inventoryRepository.save(inventory);
                })
                .orElseGet(() -> {
                    Medicine newMedicine = new Medicine(newInventory.getName(), newInventory.getUnitPrice());
                    Medicine medicine = medicineRepository.save(newMedicine);
                    inventory.setMedicine(medicine);
                    inventory.setUnits(newInventory.getUnits());
                    return inventoryRepository.save(inventory);
                });
    }

    public Inventory updateInventory(Inventory newInventory, long id) {
        return inventoryRepository.findById(id)
                .map(inventory -> {
                    inventory.setMedicine(newInventory.getMedicine());
                    inventory.setUnits(newInventory.getUnits());
                    return inventoryRepository.save(inventory);
                })
                .orElseGet(() -> {
                    newInventory.setId(id);
                    return inventoryRepository.save(newInventory);
                });
    }

}*/
