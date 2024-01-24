package com.demo.oragejobsite.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.SavedJobDao;
import com.demo.oragejobsite.entity.PostJob;
import com.demo.oragejobsite.entity.SavedJob;
import com.demo.oragejobsite.service.PostJobService;
import com.demo.oragejobsite.service.SavedJobService;


@CrossOrigin(origins = "https://job4jobless.com")
@RestController
public class SavedJobController {

	
	 @Autowired
	    private SavedJobService savedJobService;

	    @Autowired
	    private PostJobService postJobService;

	    @PostMapping("/saveJobStatus")
	    public ResponseEntity<SavedJob> saveJobStatus(@RequestBody SavedJob saveJobStatusRequest) {
	        try {
	            Optional<PostJob> postJobOptional = postJobService.getPostJobById(saveJobStatusRequest.getJobid());

	            if (postJobOptional.isPresent()) {
	                PostJob postJob = postJobOptional.get();

	                // Check if the saved job already exists for the given uid and post job
	                Optional<SavedJob> savedJobOptional = savedJobService.getSavedJobByUidAndPostJob(saveJobStatusRequest.getUid(), postJob,saveJobStatusRequest.getJobid());
	                SavedJob savedJob;

	                if (savedJobOptional.isPresent()) {
	                    // If the saved job exists, update the saveStatus
	                    savedJob = savedJobOptional.get();
	                    savedJob.setSaveStatus(saveJobStatusRequest.getSaveStatus());
	                } else {
	                    // If the saved job does not exist, create a new one
	                    savedJob = new SavedJob();
	                    savedJob.setUid(saveJobStatusRequest.getUid());
	                    savedJob.setPostJob(postJob);
	                    savedJob.setSaveStatus(saveJobStatusRequest.getSaveStatus());
	                }
	                SavedJob saved = savedJobService.saveJob(savedJob);
	                return ResponseEntity.ok(saved);
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

}
