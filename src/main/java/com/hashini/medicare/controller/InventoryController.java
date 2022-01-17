package com.hashini.medicare.controller;

import com.hashini.medicare.model.Inventory;
import com.hashini.medicare.dto.InventoryDTO;
import com.hashini.medicare.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventories")
    public List<InventoryDTO> getAllInventories(@RequestParam Optional<String> medicineName) {
        return inventoryService.getAllInventories(medicineName);
    }

    @GetMapping("/inventories/{id}")
    public Inventory getInventory(@PathVariable long id) {
        return inventoryService.getInventory(id);
    }

    @PostMapping("/inventories")
    public Inventory addInventory(@RequestBody InventoryDTO newInventory) {
        return inventoryService.addInventory(newInventory);
    }

    @PutMapping("/inventories/{id}")
    public Inventory updateInventory(@RequestBody Inventory newInventory, @PathVariable long id) {
        return inventoryService.updateInventory(newInventory, id);
    }
}