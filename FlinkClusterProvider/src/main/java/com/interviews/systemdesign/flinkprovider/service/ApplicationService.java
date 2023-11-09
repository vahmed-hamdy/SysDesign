package com.interviews.systemdesign.flinkprovider.service;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.interviews.systemdesign.flinkprovider.exception.ApplicationNotExistsException;
import com.interviews.systemdesign.flinkprovider.model.Application;
import com.interviews.systemdesign.flinkprovider.model.Cluster;
import com.interviews.systemdesign.flinkprovider.model.CreateApplicationRequest;
import com.interviews.systemdesign.flinkprovider.model.ApplicationStatus;
import com.interviews.systemdesign.flinkprovider.repository.ApplicationRepo;
import com.interviews.systemdesign.flinkprovider.repository.ClusterRepo;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationService {
//    private static String FLINK_DEPLOYMENT_FILE = "local:///opt/flink/examples/streaming/StateMachineExample.jar";

    private KubernetesClient client;

    private final ClusterService clusterService;

    private final IdGenerator idGenerator;

    private final ApplicationRepo applicationRepo;


    public Application getApplication(String id) {
        return applicationRepo.findById(id).orElseThrow(() -> new ApplicationNotExistsException("Application " + id + " doesn't exist"));
    }



    public void deleteApplication(String id) throws IOException {
        Application application = applicationRepo.findById(id).orElseThrow(() -> new ApplicationNotExistsException("Application " + id + " doesn't exist"));
        Cluster cluster = clusterService.getClusterForApp(application);
        deleteApplication(application, cluster);
        applicationRepo.delete(application);
        clusterService.clearCluster(cluster);
    }

    public ApplicationStatus CreateFlinkApplication(CreateApplicationRequest request) throws IOException {

        String id = idGenerator.getIdForApp(request.getName());
        Cluster clusterToDeploy = clusterService.allocateCluster();

        Application application = Application.builder()
                .id(id)
                .name(request.getName())
                .parallelism(request.getParallelism())
                .jarLocation(request.getJarLocation())
                .cluster(clusterToDeploy.getId())
                .build();

        startApplication(application, clusterToDeploy);
        clusterService.allocateClusterToApp(clusterToDeploy, application.getName());

        application.setStatus(ApplicationStatus.STARTING);
        applicationRepo.save(application);
       return ApplicationStatus.STARTING;
    }

    private void deleteApplication(Application application, Cluster cluster) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("FlinkSessionJob.yaml");
        StringWriter writer = new StringWriter();
        m.execute(writer,  application).flush();

        try(KubernetesClient client = createClient(cluster))
        {
            List<HasMetadata> loadedDep = client.load(new StringBufferInputStream(writer.toString())).get();
            client.resourceList(loadedDep).delete();
        }
    }
    private void startApplication(Application application, Cluster cluster) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("FlinkSessionJob.yaml");
        StringWriter writer = new StringWriter();
        m.execute(writer,  application).flush();

        try(KubernetesClient client = createClient(cluster))
        {
            List<HasMetadata> loadedDep = client.load(new StringBufferInputStream(writer.toString())).get();
            client.resourceList(loadedDep).createOrReplace();
        }
    }

    private KubernetesClient createClient(final Cluster cluster) {
        return client != null ? client : (client = new KubernetesClientBuilder()
                .withConfig(new ConfigBuilder().withConnectionTimeout(3000).build())
//                .withConfig(Config.empty())
                .build());
    }

}
