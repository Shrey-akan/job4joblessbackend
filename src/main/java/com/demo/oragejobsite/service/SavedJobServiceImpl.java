package com.demo.oragejobsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.oragejobsite.dao.SavedJobDao;
import com.demo.oragejobsite.entity.SavedJob;

@Service
public class SavedJobServiceImpl implements SavedJobService {

    @Autowired
    private SavedJobDao savedJobRepository;

    @Override
    public List<SavedJob> fetchSavedJobs(String uid) {
        return savedJobRepository.findByUid(uid);
    }

    @Override
    public SavedJob saveJob1(SavedJob savedJob) {
        // Add any additional logic or validation before saving
        return savedJobRepository.save(savedJob);
    }

	@Override
	public SavedJob saveJob(SavedJob savedJob) {
		// TODO Auto-generated method stub
		return null;
	}

    // Add other methods as needed...
}