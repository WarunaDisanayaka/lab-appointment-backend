package com.lab.labappointment.controller;


import com.lab.labappointment.entity.PatientsEntity;
import com.lab.labappointment.service.PatientsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/patients")
public class PatientsController {

    private final PatientsService patientsService;


    public PatientsController(PatientsService patientsService) {
        this.patientsService = patientsService;
    }

    @PostMapping("/create")
    public PatientsEntity createUser(@RequestBody PatientsEntity patients) {
        return patientsService.createPatients(patients);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        if (username != null && password != null) {
            Optional<PatientsEntity> authenticatedPatient = patientsService.login(username, password);

            if (authenticatedPatient.isPresent()) {
                return ResponseEntity.ok(authenticatedPatient.get());
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    // Get all users
    @GetMapping("/all")
    public List<PatientsEntity> getAllPatients() {
        return PatientsService.getAllPatients();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public PatientsEntity getPatientById(@PathVariable int id) {
        return PatientsService.getPatientById(id).orElse(null);
    }


}
