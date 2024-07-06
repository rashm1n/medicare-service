package com.hashini.medicare.controller;

import com.hashini.medicare.dto.PatientDTO;
import com.hashini.medicare.exception.NotFoundException;
import com.hashini.medicare.model.Patient;
import com.hashini.medicare.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("medicare/v1/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public List<PatientDTO> getAllPatients(@RequestParam Optional<String> searchTerm,
                                           @RequestParam Optional<String> regNo,
                                           @RequestParam int cityId) {
        return patientService.getAllPatients(searchTerm, regNo, cityId);
    }

    @PostMapping()
    public PatientDTO addPatient(@RequestBody Patient patient,
                                 @RequestParam int cityId) {
        return patientService.addPatient(patient, cityId);
    }

    @GetMapping("/{id}")
    public PatientDTO getPatient(@PathVariable long id,
                                 @RequestParam int cityId) throws NotFoundException {
        return patientService.getPatient(id, cityId);
    }

    @PutMapping("/{id}")
    public PatientDTO updatePatient(@RequestBody Patient patient,
                                    @PathVariable long id,
                                    @RequestParam int cityId) {
        return patientService.updatePatient(patient, id, cityId);
    }

    @DeleteMapping("/{id}")
    public long deletePatient(@PathVariable long id,
                              @RequestParam int cityId) throws NotFoundException {
        return patientService.deletePatient(id, cityId);
    }
}