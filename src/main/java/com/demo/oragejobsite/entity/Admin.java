package com.demo.oragejobsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
@Id
@Column(columnDefinition = "VARCHAR(36)") 
private String adminid; // Use UUID for the UID field
private String adminName;
private String adminMail;
private String adminPass;
public Admin(String adminid, String adminName, String adminMail, String adminPass) {
	super();
	this.adminid = adminid;
	this.adminName = adminName;
	this.adminMail = adminMail;
	this.adminPass = adminPass;
}
public Admin() {
	super();
	// TODO Auto-generated constructor stub
}
public String getAdminId() {
	return adminid;
}
public void setAdminId(String adminId) {
	this.adminid = adminId;
}
public String getAdminName() {
	return adminName;
}
public void setAdminName(String adminName) {
	this.adminName = adminName;
}
public String getAdminMail() {
	return adminMail;
}
public void setAdminMail(String adminMail) {
	this.adminMail = adminMail;
}
public String getAdminPass() {
	return adminPass;
}
public void setAdminPass(String adminPass) {
	this.adminPass = adminPass;
}
}
