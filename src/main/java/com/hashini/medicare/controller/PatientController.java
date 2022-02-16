package com.hashini.medicare.controller;

import com.hashini.medicare.dto.PatientDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.model.Patient;
import com.hashini.medicare.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("medicare/v1/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public List<PatientDTO> getAllPatients(@RequestParam Optional<String> patientName) {
        return patientService.getAllPatients(patientName);
    }

    @PostMapping()
    public long addPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @GetMapping("/{id}")
    public PatientDTO getPatient(@PathVariable long id) throws NotFoundException {
        return patientService.getPatient(id);
    }

    @PutMapping("/{id}")
    public long updatePatient(@RequestBody Patient patient, @PathVariable long id) {
        return patientService.updatePatient(patient, id);
    }

    @DeleteMapping("/{id}")
    public long deletePatient(@PathVariable long id) throws NotFoundException {
        return patientService.deletePatient(id);
    }
}