package com.demo.oragejobsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.oragejobsite.dao.SendMessageDao;
import com.demo.oragejobsite.entity.SendMessage;

@Service
public class MessageService {

    private final SendMessageDao sendMessageDao; // Assuming you have a SendMessageDao

    @Autowired
    public MessageService(SendMessageDao sendMessageDao) {
        this.sendMessageDao = sendMessageDao;
    }

    public SendMessage saveMessage(SendMessage message) {
        // Add any business logic or validation before saving (if needed)
        return sendMessageDao.save(message);
    }

    public List<SendMessage> getAllMessages() {
        // Add any additional logic if needed
        return sendMessageDao.findAll();
    }
}
