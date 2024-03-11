package com.lab.labappointment.service;


import com.lab.labappointment.entity.PatientsEntity;
import com.lab.labappointment.repo.PatientsRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PatientsService {

    private static PatientsRepo patientsRepo = null;
    private final PasswordEncoder passwordEncoder;


    public PatientsService(PatientsRepo patientsRepo, PasswordEncoder passwordEncoder) {
        this.patientsRepo = patientsRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public PatientsEntity createPatients(PatientsEntity patients) {
        patients.setPassword(passwordEncoder.encode(patients.getPassword()));
        return patientsRepo.save(patients);
    }

    public static List<PatientsEntity> getAllPatients() {
        return patientsRepo.findAll();
    }

    public static Optional<PatientsEntity> getPatientById(int id) {
        return patientsRepo.findById(id);
    }

    public Optional<PatientsEntity> login(String username, String password) {
        // Find a patient by username
        Optional<PatientsEntity> optionalPatient = patientsRepo.findByUsername(username);

        // Check if the patient with the given username exists
        if (optionalPatient.isPresent()) {
            PatientsEntity patient = optionalPatient.get();

            // Manually hash the entered password for comparison
            if (passwordEncoder.matches(password, patient.getPassword())) {
                return Optional.of(patient);
            }
        }

        return Optional.empty();
    }

}
