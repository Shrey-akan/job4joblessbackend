package com.demo.oragejobsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.SavedJobDao;
import com.demo.oragejobsite.entity.SavedJob;


@RestController
@CrossOrigin(origins = "https://job4jobless.com")
public class SavedJobController {

	
	 @Autowired
	 private SavedJobDao savedJobDAo;
	 
	 @PostMapping("/like")
	 public void likeJob(@RequestParam String userId, @RequestParam String jobId, @RequestParam Boolean saveStatus) {
	     SavedJob savedJob = new SavedJob();
	     savedJob.setUid(userId);
	     savedJob.setJobid(jobId);
	     savedJob.setSaveStatus(saveStatus);
	     savedJobDAo.save(savedJob);
	 }

	    @PostMapping("/unlike")
	    public void unlikeJob(@RequestParam String userId, @RequestParam String jobId) {
	    	savedJobDAo.deleteByUserIdAndJobId(userId, jobId);
	    }
}
