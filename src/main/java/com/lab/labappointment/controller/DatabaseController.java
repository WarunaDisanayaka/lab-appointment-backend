package com.lab.labappointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DatabaseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/check-database")
    public String checkDatabase() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return "Database connected!";
        } catch (Exception e) {
            return "Error while checking the database: " + e.getMessage();
        }
    }

    @GetMapping("/list-databases")
    public String listDatabases() {
        try {
            List<String> databases = jdbcTemplate.queryForList("SELECT datname FROM pg_database", String.class);
            if (!databases.isEmpty()) {
                return "List of databases: " + String.join(", ", databases);
            } else {
                return "No databases found.";
            }
        } catch (Exception e) {
            return "Error while listing databases: " + e.getMessage();
        }
    }
}
