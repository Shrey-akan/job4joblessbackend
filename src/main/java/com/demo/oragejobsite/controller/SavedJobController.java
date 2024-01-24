package com.demo.oragejobsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.SavedJobDao;
import com.demo.oragejobsite.entity.SavedJob;


@CrossOrigin(origins = "https://job4jobless.com")
@RestController
public class SavedJobController {

	
	 @Autowired
	 private SavedJobDao savedJobDAo;
	 
	 @CrossOrigin(origins = "https://job4jobless.com")
	 @PostMapping("/like")
	 public void likeJob(@RequestParam String uid, @RequestParam String jobid, @RequestParam Boolean saveStatus) {
	     SavedJob savedJob = new SavedJob();
	     savedJob.setUid(uid);
	     savedJob.setJobid(jobid);
	     savedJob.setSaveStatus(saveStatus);
	     savedJobDAo.save(savedJob);
	 }

}
