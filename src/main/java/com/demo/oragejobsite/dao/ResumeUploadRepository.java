package com.demo.oragejobsite.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.oragejobsite.entity.ResumeUpload;

public interface ResumeUploadRepository extends JpaRepository<ResumeUpload, Long> {
    // Add custom queries or methods if needed
	   ResumeUpload findByUid(String uid);
}

