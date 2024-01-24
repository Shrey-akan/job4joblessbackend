package com.demo.oragejobsite.service;

import java.util.List;

import com.demo.oragejobsite.entity.SavedJob;

public interface SavedJobService {
    List<SavedJob> fetchSavedJobs(String uid);
    SavedJob saveJob(SavedJob savedJob);
    // Add other methods as needed...
	SavedJob saveJob1(SavedJob savedJob);
}