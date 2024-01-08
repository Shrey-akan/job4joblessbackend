package com.demo.oragejobsite.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import com.demo.oragejobsite.entity.SendMessage;
import com.demo.oragejobsite.entity.UserText;
import com.demo.oragejobsite.service.MessageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WebSocketController {

//    private final SimpMessagingTemplate messagingTemplate;
//    private final MessageService messageService; // Inject the MessageService
//
//    @Autowired
//    public WebSocketController(SimpMessagingTemplate messagingTemplate, MessageService messageService) {
//        this.messagingTemplate = messagingTemplate;
//        this.messageService = messageService;
//    }
//
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
	
	private final Map<String, List<UserText>> chats = new HashMap<>();

	@MessageMapping("/chat/{chatId}")
	@SendTo("/topic/chat/{chatId}")
	public List<UserText> sendMessageWithWebsocket(@DestinationVariable String chatId,
			@Payload Message<UserText> message) {
//		log.info("new message arrived in chat with id {}", chatId);
		List<UserText> messages = this.chats.getOrDefault(chatId, new ArrayList<UserText>());
		messages.add(message.getPayload());
		chats.put(chatId, messages);
		return messages;
	}
}

