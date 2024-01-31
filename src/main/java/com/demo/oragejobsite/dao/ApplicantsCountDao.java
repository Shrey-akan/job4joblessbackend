package com.demo.oragejobsite.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.oragejobsite.entity.ApplicantsCount;



public interface ApplicantsCountDao extends MongoRepository<ApplicantsCount, String>{

	ApplicantsCount findByJobid(String jobid);

	ApplicantsCount findByJobidAndEmpid(String jobid, String empid);

}
