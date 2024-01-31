package com.demo.oragejobsite.controller;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.ApplicantsCountDao;
import com.demo.oragejobsite.dao.PostjobDao;
import com.demo.oragejobsite.dao.SavedJobDao;
import com.demo.oragejobsite.entity.ApplicantsCount;
import com.demo.oragejobsite.entity.PostJob;
import com.demo.oragejobsite.entity.SavedJob;



@CrossOrigin(origins = "https://job4jobless.com")
@RestController
public class PostjobController {
	@Autowired
	private PostjobDao pjd;
	
	
	  @Autowired
	  private SavedJobDao savedJobServiceimpl;
	  @Autowired
	    private ApplicantsCountDao applicantsCountRepository;
	  
	  @CrossOrigin(origins = "https://job4jobless.com")
	  @PostMapping("/jobpostinsert")
	  public ResponseEntity<?> jobpostinsert(@RequestBody PostJob pj) {
	      try {
	          PostJob savedPostJob = pjd.save(pj);
	          System.out.println("checking the response "+ savedPostJob.getJobid());
	          String jobid = savedPostJob.getJobid();
	          return ResponseEntity.status(HttpStatus.CREATED).body(savedPostJob.getJobid());
	      } catch (DataAccessException e) {
	          e.printStackTrace();
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	              .body(null); // Or you can return a ResponseEntity with HttpStatus and without a body
	      } catch (Exception e) {
	          e.printStackTrace();
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	              .body(null); // Or you can return a ResponseEntity with HttpStatus and without a body
	      }
	  }

	
//	@CrossOrigin(origins = "https://job4jobless.com")
//	@GetMapping("/fetchjobpost")
//	public ResponseEntity<List<PostJob>> fetchjobpost() {
//	    try {
//	        List<PostJob> jobPosts = pjd.findAll();
//	        return ResponseEntity.ok(jobPosts);
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//	    }
//	}
	  
	  @CrossOrigin(origins = "https://job4jobless.com")
	   @GetMapping("/fetchjobpost")
	    public ResponseEntity<List<PostJob>> fetchjobpost(@RequestParam(required = false) String empid) {
	        try {
	            List<PostJob> jobPosts = (empid != null && !empid.isEmpty()) ? pjd.findByEmpid(empid) : pjd.findAll();

	            for (PostJob jobPost : jobPosts) {
	                
	                int applicantsCount = getApplicantsCount(jobPost.getJobid(), empid);
	                jobPost.setApplicants(applicantsCount);
	            }

	            return ResponseEntity.ok(jobPosts);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }

	    // Helper method to fetch the count of applicants based on jobid and empid
	    private int getApplicantsCount(String jobid, String empid) {
	        try {
	            ApplicantsCount applicantsCount = applicantsCountRepository.findByJobidAndEmpid(jobid, empid);
	            System.out.print(applicantsCount);
	            return (applicantsCount != null) ? applicantsCount.getApplicants() : 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return 0;
	        }
	    }
//	  @GetMapping("/fetchjobpost")
//	  public ResponseEntity<List<PostJob>> fetchjobpost(@RequestParam(required = false) String empid) {
//	      try {
//	          List<PostJob> jobPosts;
//	          if (empid != null && !empid.isEmpty()) {
//	              jobPosts = pjd.findByEmpid(empid);
//	          } else {
//	              jobPosts = pjd.findAll();
//	          }
//	          return ResponseEntity.ok(jobPosts);
//	      } catch (Exception e) {
//	          e.printStackTrace();
//	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//	      }
//	  }

	
	
	@GetMapping("/fetchjobpoststatus")
	public ResponseEntity<List<Map<String, Object>>> fetchjobpoststatus(@RequestParam(required = false) String uid) {
	    try {
	        List<Map<String, Object>> jobPostsWithStatus = new ArrayList<>();
	        List<PostJob> allJobPosts = pjd.findAll();
	        for (PostJob postJob : allJobPosts) {
	            Map<String, Object> jobPostMap = new HashMap<>();
	            jobPostMap.put("jobid", postJob.getJobid());
	            jobPostMap.put("empName", postJob.getEmpName());
	            jobPostMap.put("empEmail", postJob.getEmpEmail());
	            jobPostMap.put("jobtitle", postJob.getJobtitle());
	            jobPostMap.put("companyforthisjob", postJob.getCompanyforthisjob());
	            jobPostMap.put("numberofopening", postJob.getNumberofopening());
	            jobPostMap.put("locationjob", postJob.getLocationjob());
	            jobPostMap.put("jobtype", postJob.getJobtype());
	            jobPostMap.put("schedulejob", postJob.getSchedulejob());
	            jobPostMap.put("payjob", postJob.getPayjob());
	            jobPostMap.put("payjobsup", postJob.getPayjobsup());
	            jobPostMap.put("descriptiondata", postJob.getDescriptiondata());
	            jobPostMap.put("empid", postJob.getEmpid());
	            jobPostMap.put("sendTime", postJob.getSendTime());
	            if (uid != null) {
	                SavedJob savedJob = savedJobServiceimpl.findByJobidAndUid(postJob.getJobid(), uid);
	                boolean saveStatus = (savedJob != null) && savedJob.getSaveStatus();
	                jobPostMap.put("saveStatus", saveStatus);
	            }

	            jobPostsWithStatus.add(jobPostMap);
	        }
	        return ResponseEntity.ok(jobPostsWithStatus);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}


	@CrossOrigin(origins = "https://job4jobless.com")
	@GetMapping("/fetchJobPostById/{jobId}")
	public ResponseEntity<PostJob> fetchJobPostById(@PathVariable String jobId) {
	    try {
	        Optional<PostJob> jobPost = pjd.findById(jobId);
	        if (jobPost.isPresent()) {
	            return ResponseEntity.ok(jobPost.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

	 
	@CrossOrigin(origins = "https://job4jobless.com")
    @PutMapping("/jobpostupdate/{jobid}")
    public ResponseEntity<Object> jobpostupdate(@PathVariable String jobid, @RequestBody PostJob updatedJob) {
        try {
            Optional<PostJob> existingJob = pjd.findById(jobid);
            if (existingJob.isPresent()) {
                PostJob currentJob = existingJob.get();
                currentJob.setJobtitle(updatedJob.getJobtitle());
                currentJob.setEmpName(updatedJob.getEmpName());
                currentJob.setEmpEmail(updatedJob.getEmpEmail());
               
                currentJob.setCompanyforthisjob(updatedJob.getCompanyforthisjob());
                currentJob.setNumberofopening(updatedJob.getNumberofopening());
                currentJob.setLocationjob(updatedJob.getLocationjob());
                currentJob.setJobtype(updatedJob.getJobtype());
                currentJob.setSchedulejob(updatedJob.getSchedulejob());
                currentJob.setPayjob(updatedJob.getPayjob());
                currentJob.setPayjobsup(updatedJob.getPayjobsup());
                currentJob.setDescriptiondata(updatedJob.getDescriptiondata());
                pjd.save(currentJob);
                return ResponseEntity.status(HttpStatus.OK).body(currentJob);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
        }
    }
	
	
	
	@CrossOrigin(origins = "https://job4jobless.com", methods = { RequestMethod.PUT })
	@PutMapping("/updateJobStatus/{jobid}")
	public ResponseEntity<Object> updateJobStatus(@PathVariable String jobid, @RequestBody PostJob updatedJob) {
	    try {
	        Optional<PostJob> existingJob = pjd.findById(jobid);
	        if (existingJob.isPresent()) {
	            PostJob currentJob = existingJob.get();
	      
	            pjd.save(currentJob);
	            return ResponseEntity.status(HttpStatus.OK).body(currentJob);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
	        }
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
	    }
	}

}

