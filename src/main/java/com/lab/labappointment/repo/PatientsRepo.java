package com.lab.labappointment.repo;

import com.lab.labappointment.entity.PatientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepo extends JpaRepository<PatientsEntity, Integer> {
}
