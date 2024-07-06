package com.hashini.medicare.controller;

import com.hashini.medicare.dto.MedicineDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.service.MedicineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicare/v1/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping()
    public List<MedicineDTO> getAllMedicines(@RequestParam Optional<String> medicineName,
                                             @RequestParam Optional<Boolean> lowInventory,
                                             @RequestParam int cityId) {
        return medicineService.getAllMedicines(medicineName, lowInventory.orElse(false), cityId);
    }

    @GetMapping("/{id}")
    public MedicineDTO getMedicine(@PathVariable long id,
                                   @RequestParam int cityId) throws NotFoundException {
        return medicineService.getMedicine(id, cityId);
    }

    @PostMapping()
    public int addMedicine(@RequestBody MedicineDTO newMedicine,
                           @RequestParam int cityId) throws NotFoundException {
        return medicineService.addMedicine(newMedicine, cityId);
    }

    @PutMapping("/{id}")
    public int updateMedicine(@RequestBody MedicineDTO newMedicine, @PathVariable long id,
                              @RequestParam int cityId) throws NotFoundException {
        return medicineService.updateMedicine(newMedicine, id, cityId);
    }

    @DeleteMapping("/{id}")
    public long deleteMedicine(@PathVariable long id,
                               @RequestParam int cityId) throws NotFoundException {
        return medicineService.deleteMedicine(id, cityId);
    }

}