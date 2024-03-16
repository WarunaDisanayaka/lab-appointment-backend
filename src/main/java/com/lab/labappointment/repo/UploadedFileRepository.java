package com.lab.labappointment.repo;

import com.lab.labappointment.entity.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
    // Additional custom query methods can be defined here if needed
}
