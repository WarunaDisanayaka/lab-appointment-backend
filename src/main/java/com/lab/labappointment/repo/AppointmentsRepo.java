package com.lab.labappointment.repo;

import com.lab.labappointment.entity.AppointmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepo extends JpaRepository<AppointmentsEntity, Integer> {


}
