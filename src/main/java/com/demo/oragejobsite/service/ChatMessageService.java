package com.demo.oragejobsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.oragejobsite.dao.MessageEntityRepository;
import com.demo.oragejobsite.entity.ChatMessage;
import com.demo.oragejobsite.entity.SendMessage;

@Service
public class ChatMessageService {

    @Autowired
    private MessageEntityRepository messageEntityRepository;

    public void saveMessage(ChatMessage chatMessage) {
    	SendMessage messageEntity = convertToEntity(chatMessage);
        messageEntityRepository.save(messageEntity);
    }

    public void saveJoinMessage(ChatMessage chatMessage) {
        // Optionally, perform specific operations for join messages
        saveMessage(chatMessage);
    }

    private SendMessage convertToEntity(ChatMessage chatMessage) {
    	SendMessage messageEntity = new SendMessage();
        messageEntity.setMessageTo(chatMessage.getMessageTo());
        messageEntity.setMessageFrom(chatMessage.getSender());
        messageEntity.setMessage(chatMessage.getContent());
       
        return messageEntity;
    }
}
