package com.demo.oragejobsite.socket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.entity.SendMessage;
import com.demo.oragejobsite.service.MessageService;

@RestController
@CrossOrigin(origins = "https://job4jobless.com")
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @Autowired
    public WebSocketController(SimpMessagingTemplate messagingTemplate, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
    }

    @MessageMapping("/send")
    public void sendMessage(@Payload SendMessage message) {
        SendMessage savedMessage = messageService.saveMessage(message);
        messagingTemplate.convertAndSend("/topic/messages", savedMessage);
    }

    @GetMapping("/fetchMessages")
    public ResponseEntity<List<SendMessage>> fetchMessages() {
        try {
            List<SendMessage> messages = messageService.getAllMessages();
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @MessageMapping("/chat")
    public void chatMessage(@Payload SendMessage message) {
        SendMessage savedMessage = messageService.saveMessage(message);
        messagingTemplate.convertAndSend("/topic/chat", savedMessage);
    }

    @GetMapping("/fetchChatMessages")
    public ResponseEntity<List<SendMessage>> fetchChatMessages() {
        try {
            List<SendMessage> chatMessages = messageService.getAllMessages();
            return new ResponseEntity<>(chatMessages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
