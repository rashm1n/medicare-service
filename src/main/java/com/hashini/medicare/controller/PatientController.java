package com.hashini.medicare.controller;

import com.hashini.medicare.model.Patient;
import com.hashini.medicare.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients(@RequestParam Optional<String> patientName) {
        return patientService.getAllPatients(patientName);
    }

    @GetMapping("/patients/{id}")
    public Patient getPatient(@PathVariable long id) {
        return patientService.getPatient(id);
    }

    @PostMapping("/patients")
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }
}