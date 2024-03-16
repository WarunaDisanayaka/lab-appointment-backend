package com.lab.labappointment.controller;

import com.lab.labappointment.entity.PatientsEntity;
import com.lab.labappointment.entity.UploadedFile;
import com.lab.labappointment.repo.PatientsRepo;
import com.lab.labappointment.repo.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {

    private static final String UPLOAD_DIRECTORY = "uploads";

    @Autowired
    private UploadedFileRepository fileRepository;

    @Autowired
    private PatientsRepo patientsRepository; // Assuming you have a PatientsRepository

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("patientId") int patientId) {
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIRECTORY + "/" + file.getOriginalFilename());
            Files.write(path, bytes);

            UploadedFile uploadedFile = new UploadedFile();
            uploadedFile.setFileName(file.getOriginalFilename());
            uploadedFile.setFilePath(path.toString());

            // Fetch patient from database using patientId
            PatientsEntity patient = patientsRepository.findById(patientId).orElse(null);
            if (patient == null) {
                return "Patient not found.";
            }

            uploadedFile.setPatient(patient);

            fileRepository.save(uploadedFile);

            return "File uploaded successfully.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file.";
        }
    }
}
