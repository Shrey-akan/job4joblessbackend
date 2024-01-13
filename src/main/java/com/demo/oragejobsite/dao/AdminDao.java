package com.demo.oragejobsite.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.Admin;
import com.demo.oragejobsite.entity.Employer;

@Repository
public interface AdminDao extends JpaRepository<Admin, String>{

	Admin findByAdminMail(String adminMail);
	Optional<Admin> findByAdminid(String adminId);

}