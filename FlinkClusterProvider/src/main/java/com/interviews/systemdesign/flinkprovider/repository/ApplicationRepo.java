package com.interviews.systemdesign.flinkprovider.repository;

import com.interviews.systemdesign.flinkprovider.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationRepo extends MongoRepository<Application, String> {
}
