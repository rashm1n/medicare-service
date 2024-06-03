package com.hashini.medicare.controller;

import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.service.MedicineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/medicare/v1/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping()
    public List<MedicineDTO> getAllMedicines(@RequestParam Optional<String> medicineName,
                                             @RequestParam Optional<Boolean> lowInventory) {
        return medicineService.getAllMedicines(medicineName, lowInventory.orElse(false));
    }

    @GetMapping("/{id}")
    public MedicineDTO getMedicine(@PathVariable long id) throws NotFoundException {
        return medicineService.getMedicine(id);
    }

    @PostMapping()
    public int addMedicine(@RequestBody MedicineDTO newMedicine) throws NotFoundException {
        return medicineService.addMedicine(newMedicine);
    }

    @PutMapping("/{id}")
    public int updateMedicine(@RequestBody MedicineDTO newMedicine, @PathVariable long id) throws NotFoundException {
        return medicineService.updateMedicine(newMedicine, id);
    }

    @DeleteMapping("/{id}")
    public long deleteMedicine(@PathVariable long id) throws NotFoundException {
        return medicineService.deleteMedicine(id);
    }

}