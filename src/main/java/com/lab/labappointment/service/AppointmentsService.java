package com.lab.labappointment.service;

import com.lab.labappointment.entity.AppointmentsEntity;
import com.lab.labappointment.repo.AppointmentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentsService {

    private final AppointmentsRepo appointmentsRepo;

    @Autowired
    public AppointmentsService(AppointmentsRepo appointmentsRepo) {
        this.appointmentsRepo = appointmentsRepo;
    }

    public List<AppointmentsEntity> getAllAppointments() {
        return appointmentsRepo.findAll();
    }

    public Optional<AppointmentsEntity> getAppointmentById(int id) {
        return appointmentsRepo.findById(id);
    }

    public AppointmentsEntity createAppointment(AppointmentsEntity appointment) {
        return appointmentsRepo.save(appointment);
    }

    public AppointmentsEntity updateAppointment(int id, AppointmentsEntity updatedAppointment) {
        if (appointmentsRepo.existsById(id)) {
            updatedAppointment.setAppointmentId(id);
            return appointmentsRepo.save(updatedAppointment);
        }
        return null; // Handle not found scenario
    }

    public void deleteAppointment(int id) {
        appointmentsRepo.deleteById(id);
    }
}
