package com.demo.oragejobsite.socket;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.entity.ChatMessage;


import com.demo.oragejobsite.service.MessageService;

@RestController
@CrossOrigin(origins = "https://job4jobless.com")
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService; // Inject the MessageService

    @Autowired
    public WebSocketController(SimpMessagingTemplate messagingTemplate, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
    }

//    @MessageMapping("/send")
//    public void sendMessage(@Payload SendMessage message) {
//        // Save the message to the database (you can use your existing service)
//        SendMessage savedMessage = messageService.saveMessage(message);
//        
//        // Broadcast the message to all subscribers
//        messagingTemplate.convertAndSend("/topic/messages", savedMessage);
//    }
//
//    @GetMapping("/fetchMessages")
//    public ResponseEntity<List<SendMessage>> fetchMessages() {
//        try {
//            List<SendMessage> messages = messageService.getAllMessages();
//            return new ResponseEntity<>(messages, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
    
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
//	
}

