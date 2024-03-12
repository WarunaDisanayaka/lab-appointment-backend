package com.lab.labappointment.repo;

import com.lab.labappointment.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<AdminEntity, Long> {

    Optional<AdminEntity> findByUsername(String username);
}
