package com.hashini.medicare.controller;

import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.model.Medicine;
import com.hashini.medicare.service.MedicineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/medicines")
    public List<MedicineDTO> getAllMedicines(@RequestParam Optional<String> medicineName) {
        return medicineService.getAllMedicines(medicineName);
    }

    @GetMapping("/medicines/{id}")
    public MedicineDTO getMedicine(@PathVariable long id) {
        return medicineService.getMedicine(id);
    }

    @PostMapping("/medicines")
    public Medicine addMedicine(@RequestBody MedicineDTO newMedicine) {
        return medicineService.addMedicine(newMedicine);
    }

    @PutMapping("/medicines/{id}")
    public Medicine updateMedicine(@RequestBody Medicine newMedicine, @PathVariable long id) {
        return medicineService.updateMedicine(newMedicine, id);
    }

}