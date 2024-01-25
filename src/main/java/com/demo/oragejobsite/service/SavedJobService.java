package com.demo.oragejobsite.service;

import java.util.List;
import java.util.Optional;

import com.demo.oragejobsite.entity.PostJob;
import com.demo.oragejobsite.entity.SavedJob;

public interface SavedJobService {
    List<SavedJob> fetchSavedJobs(String uid);
    SavedJob saveJob(SavedJob savedJob);
	Optional<SavedJob> getSavedJobByUidAndPostJob(String uid, PostJob postJob, String jobid);
}