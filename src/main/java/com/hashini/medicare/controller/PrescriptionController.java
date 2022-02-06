package com.hashini.medicare.controller;

import com.hashini.medicare.dto.PrescriptionCreationDTO;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.model.Prescription;
import com.hashini.medicare.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public int addPrescription(@RequestBody Prescription prescription) throws Exception {
        try {
            return prescriptionService.addPrescription(prescription);
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found", ex);
        }
    }

    @GetMapping
    public List<PrescriptionDTO> getAllPrescriptions(@RequestParam Optional<Boolean> processed) {
        return prescriptionService.getAllPrescriptions(processed);
    }

    @GetMapping("/{id}")
    public PrescriptionDTO getPrescription(@PathVariable long id) throws Exception {
        try {
            return prescriptionService.getPrescription(id);
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prescription not found", ex);
        }
    }
}