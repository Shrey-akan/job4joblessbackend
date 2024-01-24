package com.demo.oragejobsite.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "saved_job")
public class SavedJob {
	@Id
    private String saveId;
	private String uid;
	private String jobid;
	private Boolean saveStatus;
	public SavedJob() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SavedJob(String saveId, String uid, String jobid, Boolean saveStatus) {
		super();
		this.saveId = saveId;
		this.uid = uid;
		this.jobid = jobid;
		this.saveStatus = saveStatus;
	}
	public String getSaveId() {
		return saveId;
	}
	public void setSaveId(String saveId) {
		this.saveId = saveId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public Boolean getSaveStatus() {
		return saveStatus;
	}
	public void setSaveStatus(Boolean saveStatus) {
		this.saveStatus = saveStatus;
	}
	
	
	
	
}
