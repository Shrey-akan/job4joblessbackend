package com.demo.oragejobsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.UserStatusDao;
import com.demo.oragejobsite.entity.UserStatus;


@RestController
@CrossOrigin(origins = "https://job4jobless.com")
public class UserStatusController {

	@Autowired
	private UserStatusDao userstatdao;
	
	   @CrossOrigin(origins = "https://job4jobless.com")
	   @GetMapping("/countTrueStatus")
	   public ResponseEntity<Integer> countTrueStatus(@RequestParam String uid) {
		    try {
		    	System.out.println("Received uid: " + uid);
		        List<UserStatus> statusList = userstatdao.findByUid(uid);
		     	System.out.println("Checking the statuslist: " + statusList);
		        System.out.println("checking the statuslist "+ statusList);
		        int trueCount = (int) statusList.stream()
		                .filter(userStatus -> Boolean.TRUE.equals(userStatus.getViewcheck()))
		                .count();
		        return ResponseEntity.ok(trueCount);
		    } catch (Exception e) {
		        e.printStackTrace();
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		    }
		}
	   
	   
	   @PutMapping("/updateViewCheck")
	    public ResponseEntity<String> updateViewCheck(@RequestParam String uid, @RequestParam String juid) {
	        try {
	            UserStatus userStatus = userstatdao.findByUidAndJuid(uid, juid);
	            System.out.println(userStatus);
	            if (userStatus != null) {
	                userStatus.setViewcheck(false);
	                userstatdao.save(userStatus);
	                System.out.println(userStatus.getViewcheck());
	                return ResponseEntity.ok("ViewCheck updated successfully.");
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserStatus not found for uid: " + uid + " and juid: " + juid);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
	        }
	    }
	
}
