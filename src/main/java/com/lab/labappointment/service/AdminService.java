package com.lab.labappointment.service;

import com.lab.labappointment.entity.AdminEntity;
import com.lab.labappointment.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepo adminRepo;


    @Autowired
    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;

    }

    public AdminEntity register(AdminEntity admin) {

        return adminRepo.save(admin);
    }

    public Optional<AdminEntity> login(String username, String password) {
        Optional<AdminEntity> optionalAdmin = adminRepo.findByUsername(username);

        if (optionalAdmin.isPresent()) {
            AdminEntity admin = optionalAdmin.get();

            // Use the password encoder to check if the entered password matches the stored encoded password
            if (password.equals(admin.getPassword())) {
                return Optional.of(admin);
            }
        }

        return Optional.empty();
    }
}
