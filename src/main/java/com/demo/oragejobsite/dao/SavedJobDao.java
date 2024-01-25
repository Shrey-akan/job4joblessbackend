package com.demo.oragejobsite.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.oragejobsite.entity.SavedJob;


public interface SavedJobDao extends MongoRepository<SavedJob, String>{

	List<SavedJob> findByUid(String uid);

	SavedJob findByJobidAndUid(String jobid, String uid);
}
