package com.hashini.medicare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicare/v1/health")

public class HealthController {

    @GetMapping
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("All services are running");
    }
}