package com.hashini.medicare.controller;

import com.hashini.medicare.model.Prescription;
import com.hashini.medicare.dto.PrescriptionDTO;
import com.hashini.medicare.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping("/prescriptions")
    public Prescription addPrescription(@RequestBody PrescriptionDTO prescriptionInfo)  {
            return prescriptionService.addPrescription(prescriptionInfo);
    }

    @GetMapping("/prescriptions")
    public List<Prescription> getAllPatients() {
        return prescriptionService.getAllPatients();
    }
}