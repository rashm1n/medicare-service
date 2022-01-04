package com.hashini.medicare.controller;

import com.hashini.medicare.model.Medicine;
import com.hashini.medicare.service.MedicineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/medicines")
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    @PostMapping("/medicines")
    public Medicine addMedicine(@RequestBody Medicine newMedicine) {
        return medicineService.addMedicine(newMedicine);
    }

    @PutMapping("/medicines/{id}")
    public Medicine updateMedicine(@RequestBody Medicine newMedicine, @PathVariable long id) {
        return medicineService.updateMedicine(newMedicine, id);
    }

}