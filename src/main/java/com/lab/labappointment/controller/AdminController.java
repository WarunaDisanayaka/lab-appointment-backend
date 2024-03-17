package com.lab.labappointment.controller;

import com.lab.labappointment.entity.AdminEntity;
import com.lab.labappointment.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public AdminEntity register(@RequestBody AdminEntity admin) {
        return adminService.register(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        if (username != null && password != null) {
            Optional<AdminEntity> authenticatedAdmin = adminService.login(username, password);

            if (authenticatedAdmin.isPresent()) {
                return ResponseEntity.ok(authenticatedAdmin.get());
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}
