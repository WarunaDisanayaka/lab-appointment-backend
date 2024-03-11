package com.lab.labappointment.controller;


import com.lab.labappointment.entity.PatientsEntity;
import com.lab.labappointment.service.PatientsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
