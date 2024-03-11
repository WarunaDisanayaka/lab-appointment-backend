package com.lab.labappointment.service;


import com.lab.labappointment.entity.PatientsEntity;
import com.lab.labappointment.repo.PatientsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientsService {

    private static PatientsRepo patientsRepo = null;

    public PatientsService(PatientsRepo patientsRepo) {
        this.patientsRepo = patientsRepo;
    }

    public PatientsEntity createPatients(PatientsEntity patients) {
        return patientsRepo.save(patients);
    }

    public static List<PatientsEntity> getAllPatients() {
        return patientsRepo.findAll();
    }

    public static Optional<PatientsEntity> getPatientById(int id) {
        return patientsRepo.findById(id);
    }

}
