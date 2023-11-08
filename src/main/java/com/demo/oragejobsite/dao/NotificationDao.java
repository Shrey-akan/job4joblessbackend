package com.demo.oragejobsite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.oragejobsite.entity.Notification;

@Repository
public interface NotificationDao extends JpaRepository<Notification, Long>{

}

