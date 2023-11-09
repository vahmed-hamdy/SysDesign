package com.interviews.systemdesign.flinkprovider.service;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.interviews.systemdesign.flinkprovider.exception.ClusterAlreadyAllocatedException;
import com.interviews.systemdesign.flinkprovider.exception.ClusterNotExistsException;
import com.interviews.systemdesign.flinkprovider.model.Application;
import com.interviews.systemdesign.flinkprovider.model.Cluster;
import com.interviews.systemdesign.flinkprovider.model.ClusterStatus;
import com.interviews.systemdesign.flinkprovider.model.CreateClusterRequest;
import com.interviews.systemdesign.flinkprovider.repository.ClusterRepo;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ClusterService {

    private KubernetesClient client;
    @Autowired
    private final ClusterRepo clusterRepo;

    public void createCluster(CreateClusterRequest request) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("FlinkDeployment.yaml");
        StringWriter writer = new StringWriter();
        m.execute(writer,  request).flush();

        Cluster cluster = Cluster.builder()
                .id(request.getId())
                .cluster(request.getCluster())
                .namespace(request.getNamespace())
                .status(ClusterStatus.AVAILABLE)
                .build();

        try(KubernetesClient client = createClient(cluster))
        {
            List<HasMetadata> loadedDep = client.load(new StringBufferInputStream(writer.toString())).get();
            client.resourceList(loadedDep).createOrReplace();
            clusterRepo.save(cluster);
        }
    }

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

    public Cluster getCluster(final String clusterId) {
        return clusterRepo.findById(clusterId).orElseThrow(() -> new ClusterNotExistsException("NO Cluster with Id " + clusterId));
    }
    public Cluster getClusterForApp(final Application application) {
        return clusterRepo.getClustersForApp(application.getId()).stream().findFirst().orElseThrow(() -> new RuntimeException("aaaa"));
    }

    public void clearCluster(Cluster cluster) {
        cluster.setApplicationId(null);
        cluster.setStatus(ClusterStatus.AVAILABLE);
        clusterRepo.save(cluster);
    }

    private KubernetesClient createClient(final Cluster cluster) {
        return client != null ? client : (client = new KubernetesClientBuilder()
//                .withConfig(Config.empty())
                .build());
    }
}
