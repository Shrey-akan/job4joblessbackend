package com.demo.oragejobsite.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.oragejobsite.dao.ResumeUploadRepository;
import com.demo.oragejobsite.entity.ResumeUpload;

@RestController
@CrossOrigin(origins = "https://job4jobless.com")
public class FileUploadController {

    @Autowired
    private ResumeUploadRepository resumeUploadRepository; // Create a repository for the ResumeUpload entity

    @PostMapping("/uploadPdf")
    @CrossOrigin(origins = "https://job4jobless.com")
    public String uploadPdf(@RequestParam("file") MultipartFile file, @RequestParam("uid") String uid) {
        if (!file.isEmpty()) {
            try {
                String uploadDirectory = "/root/folder_name/upload_pdf/";
                String originalFileName = file.getOriginalFilename();

                // Create the directory if it doesn't exist
                new File(uploadDirectory).mkdirs();

                String filePath = uploadDirectory + originalFileName;
                file.transferTo(new File(filePath));

                // Save the file information to the database along with the UID
                ResumeUpload resumeUpload = new ResumeUpload();
                resumeUpload.setUid(uid);
                resumeUpload.setFileName(originalFileName);
                // Set other fields as needed

                resumeUploadRepository.save(resumeUpload);

                return "File uploaded successfully with UID: " + uid;
            } catch (IOException e) {
                return "File upload failed: " + e.getMessage();
            }
        } else {
            return "File is empty.";
        }
    }
    
    @GetMapping("/fetchByUid")
    @CrossOrigin(origins = "https://job4jobless.com")
    public ResponseEntity<ResumeUpload> fetchByUid(@RequestParam("uid") String uid) {
        try {
            ResumeUpload resumeUpload = resumeUploadRepository.findByUid(uid);

            if (resumeUpload != null) {
                return ResponseEntity.ok(resumeUpload);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
