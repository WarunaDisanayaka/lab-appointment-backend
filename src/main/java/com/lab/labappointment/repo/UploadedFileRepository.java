package com.lab.labappointment.repo;

import com.lab.labappointment.entity.PatientsEntity;
import com.lab.labappointment.entity.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
    List<UploadedFile> findByPatient(PatientsEntity patient);

}
