package com.demo.oragejobsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.SendMessageDao;
import com.demo.oragejobsite.entity.SendMessage;
import com.demo.oragejobsite.service.MessageService;

@RestController
@CrossOrigin(origins = "https://job4jobless.com")
public class SendMessageController {
	@Autowired
	private SendMessageDao sendmessage;
	
	
	
	 private final MessageService messageService;

   @Autowired
    public SendMessageController(MessageService messageService) {
       this.messageService = messageService;
    }

   @CrossOrigin(origins = "https://job4jobless.com")
	    @PostMapping("/send")
    public ResponseEntity<SendMessage> sendMessage(@RequestBody SendMessage message) {
        try {
            SendMessage savedMessage = messageService.saveMessage(message);
           return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception or handle it appropriately
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	    
   @CrossOrigin(origins = "https://job4jobless.com")
	    @GetMapping("/fetchMessages")
   public ResponseEntity<List<SendMessage>> fetchMessages() {
       try {
           List<SendMessage> messages = messageService.getAllMessages();
            return new ResponseEntity<>(messages, HttpStatus.OK);
      } catch (Exception e) {
           // Log the exception or handle it appropriately
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

	    
	    
	
}
