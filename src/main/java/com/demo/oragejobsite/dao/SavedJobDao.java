package com.demo.oragejobsite.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.oragejobsite.entity.SavedJob;


public interface SavedJobDao extends MongoRepository<SavedJob, String>{


}
