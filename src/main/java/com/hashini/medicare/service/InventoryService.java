package com.hashini.medicare.service;

import com.hashini.medicare.model.Inventory;
import com.hashini.medicare.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventory(long id) {
        return inventoryRepository.findById(id).get();
    }

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
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

}