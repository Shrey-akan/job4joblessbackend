package com.demo.oragejobsite.entity;

import javax.persistence.*;

@Entity
@Table(name = "resume_upload")
public class ResumeUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid")
    private String uid;

    @Column(name = "file_name")
    private String fileName;

	public ResumeUpload(Long id, String uid, String fileName) {
		super();
		this.id = id;
		this.uid = uid;
		this.fileName = fileName;
	}

	public ResumeUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

    // Other fields, getters, and setters
    
    
    
}

