package com.hashini.medicare.controller;

import com.hashini.medicare.model.Inventory;
import com.hashini.medicare.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventories")
    public List<Inventory> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @GetMapping("/inventories/{id}")
    public Inventory getInventory(@PathVariable long id) {
        return inventoryService.getInventory(id);
    }

    @PostMapping("/inventories")
    public Inventory addInventory(@RequestBody Inventory newInventory) {
        return inventoryService.addInventory(newInventory);
    }

    @PutMapping("/inventories/{id}")
    public Inventory updateInventory(@RequestBody Inventory newInventory, @PathVariable long id) {
        return inventoryService.updateInventory(newInventory, id);
    }
}