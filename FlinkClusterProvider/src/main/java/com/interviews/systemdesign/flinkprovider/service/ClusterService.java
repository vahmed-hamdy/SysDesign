package com.interviews.systemdesign.flinkprovider.service;

import com.interviews.systemdesign.flinkprovider.exception.ClusterAlreadyAllocatedException;
import com.interviews.systemdesign.flinkprovider.model.Application;
import com.interviews.systemdesign.flinkprovider.model.Cluster;
import com.interviews.systemdesign.flinkprovider.model.ClusterStatus;
import com.interviews.systemdesign.flinkprovider.repository.ClusterRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class ClusterService {

    @Autowired
    private final ClusterRepo clusterRepo;

    public Cluster allocateCluster() {
        /// TODO handle concurrency
        return clusterRepo.getAvailableClusters().get(0);
    }

    public void allocateClusterToApp(Cluster cluster, String applicationId) {
        if (!Objects.equals(cluster.getApplicationId(), applicationId) && cluster.getApplicationId() != null) {
           throw new ClusterAlreadyAllocatedException(String.format("Cluster %s is not available", cluster.getId()));
        }

        cluster.setApplicationId(applicationId);
        cluster.setStatus(ClusterStatus.DEPLOYED);
        clusterRepo.save(cluster);
    }

    public Cluster getClusterForApp(final Application application) {
        return clusterRepo.getClustersForApp(application.getId()).stream().findFirst().orElseThrow(() -> new RuntimeException("aaaa"));
    }

    public void clearCluster(Cluster cluster) {
        cluster.setApplicationId(null);
        cluster.setStatus(ClusterStatus.AVAILABLE);
        clusterRepo.save(cluster);
    }
}
