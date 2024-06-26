package com.hashini.medicare.controller;

import com.hashini.medicare.dto.PrescriptionCreationDTO;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.model.Prescription;
import com.hashini.medicare.service.PrescriptionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/medicare/v1/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public long addPrescription(@RequestBody PrescriptionCreationDTO prescriptionInfo,
                                @RequestParam int cityId) {
        try {
            return prescriptionService.addPrescription(prescriptionInfo, cityId);
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @GetMapping
    public List<PrescriptionDTO> getAllPrescriptions(@RequestParam Optional<Boolean> processed,
                                                     @RequestParam Optional<String> searchTerm,
                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate,
                                                     @RequestParam int cityId) {
        return prescriptionService.getAllPrescriptions(processed, searchTerm, startDate, endDate, cityId);
    }

    @GetMapping("/{id}")
    public PrescriptionDTO getPrescription(@PathVariable long id,
                                           @RequestParam int cityId) {
        try {
            return prescriptionService.getPrescription(id, cityId);
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PutMapping("/{id}")
    public long updatePrescription(@RequestBody Prescription prescription,
                                   @PathVariable long id,
                                   @RequestParam int cityId) {
        return prescriptionService.updatePrescription(prescription, id, cityId);
    }
}