package com.lab.labappointment.controller;

import com.lab.labappointment.entity.AdminEntity;
import com.lab.labappointment.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
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
    public Optional<AdminEntity> login(@RequestBody AdminEntity admin) {
        return adminService.login(admin.getUsername(), admin.getPassword());
    }
}
