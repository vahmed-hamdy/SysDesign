package com.interviews.systemdesign.flinkprovider.repository;

import com.interviews.systemdesign.flinkprovider.model.Cluster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ClusterRepo  extends MongoRepository<Cluster, String> {
    @Query("{ 'status' : AVAILABLE}")
    List<Cluster> getAvailableClusters();
    @Query("{ 'applicationId' : ?0 }")
    List<Cluster> getClustersForApp(String applicationId);
}
