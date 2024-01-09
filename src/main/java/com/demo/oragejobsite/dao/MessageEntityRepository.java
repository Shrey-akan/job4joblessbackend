package com.demo.oragejobsite.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.oragejobsite.entity.SendMessage;

public interface MessageEntityRepository extends JpaRepository<SendMessage, Long> {

    // You can add custom queries here if needed

}
