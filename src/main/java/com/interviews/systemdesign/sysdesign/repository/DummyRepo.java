package com.interviews.systemdesign.sysdesign.repository;


import com.interviews.systemdesign.sysdesign.model.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DummyRepo extends MongoRepository<Pair, String> {}
