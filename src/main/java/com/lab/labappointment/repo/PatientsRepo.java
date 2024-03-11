package com.lab.labappointment.repo;

import com.lab.labappointment.entity.PatientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientsRepo extends JpaRepository<PatientsEntity, Integer> {
    Optional<PatientsEntity> findByUsername(String username);
}
