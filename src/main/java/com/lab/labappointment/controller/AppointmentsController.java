package com.lab.labappointment.controller;

import com.lab.labappointment.entity.AppointmentsEntity;
import com.lab.labappointment.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/appointments")
public class AppointmentsController {

    private final AppointmentsService appointmentsService;

    @Autowired
    public AppointmentsController(AppointmentsService appointmentsService) {
        this.appointmentsService = appointmentsService;
    }

    @GetMapping("/all")
    public List<AppointmentsEntity> getAllAppointments() {
        return appointmentsService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentsEntity> getAppointmentById(@PathVariable int id) {
        return appointmentsService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentsEntity> createAppointment(@RequestBody AppointmentsEntity appointment) {
        AppointmentsEntity createdAppointment = appointmentsService.createAppointment(appointment);
        return ResponseEntity.ok(createdAppointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentsEntity> updateAppointment(
            @PathVariable int id,
            @RequestBody AppointmentsEntity updatedAppointment
    ) {
        AppointmentsEntity result = appointmentsService.updateAppointment(id, updatedAppointment);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int id) {
        appointmentsService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }
}
