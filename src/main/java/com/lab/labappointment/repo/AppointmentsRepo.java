package com.lab.labappointment.repo;

import com.lab.labappointment.entity.AppointmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentsRepo extends JpaRepository<AppointmentsEntity, Integer> {

    Optional<AppointmentsEntity> findTopByOrderByAppointmentNumberDesc();

    List<AppointmentsEntity> findByPatientPatientId(int patientId);

    int countAppointmentsByStatus(String status);


}
