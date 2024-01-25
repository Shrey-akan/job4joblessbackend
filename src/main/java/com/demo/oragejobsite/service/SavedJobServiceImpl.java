package com.demo.oragejobsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.oragejobsite.dao.PostjobDao;
import com.demo.oragejobsite.dao.SavedJobDao;
import com.demo.oragejobsite.entity.PostJob;
import com.demo.oragejobsite.entity.SavedJob;

@Service
public class SavedJobServiceImpl implements SavedJobService {

    @Autowired
    private SavedJobDao savejobdao;
	@Autowired
	private PostjobDao postjobdao;
    @Override
    public SavedJob updateSavedJobStatus(String jobid, String uid, Boolean status) {
    	 PostJob postJob = postjobdao.findByJobid(jobid);
    	 if (postJob != null) {
    	        SavedJob savedJob = savejobdao.findByJobidAndUid(jobid, uid);

    	        if (savedJob != null) {
    	            savedJob.setSaveStatus(status);
    	            return savejobdao.save(savedJob);
    	        } else {
    	            // If SavedJob doesn't exist, create a new one
    	            SavedJob newSavedJob = new SavedJob();
    	            newSavedJob.setJobid(jobid);
    	            newSavedJob.setUid(uid);
    	            newSavedJob.setSaveStatus(status);
    	            newSavedJob.setPostJob(postJob); // Link the PostJob
    	            return savejobdao.save(newSavedJob);
    	        }
    	    }
    	    return null;
    }

}
