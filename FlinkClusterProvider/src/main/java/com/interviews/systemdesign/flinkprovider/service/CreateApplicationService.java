package com.interviews.systemdesign.flinkprovider.service;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.interviews.systemdesign.flinkprovider.model.CreateApplicationRequest;
import com.interviews.systemdesign.flinkprovider.model.CreateApplicationStatus;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class CreateApplicationService {
    private static String FLINK_DEPLOYMENT_FILE = "local:///opt/flink/examples/streaming/StateMachineExample.jar";

    final KubernetesClient client;

    public CreateApplicationService() {
        client = new KubernetesClientBuilder().build();
    }

    public CreateApplicationStatus createFlinkDeployment(CreateApplicationRequest request) throws IOException {
//
        request.setJarLocation(FLINK_DEPLOYMENT_FILE);
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("FlinkDeployment.yaml");
        StringWriter writer = new StringWriter();
        m.execute(writer,  request).flush();


        List<HasMetadata> loadedDep = client.load(new StringBufferInputStream(writer.toString())).get();
//        client.genericKubernetesResources()

        client.resourceList(loadedDep).createOrReplace();
       return CreateApplicationStatus.RECONCILING;
    }

    public static void main(String []args) throws IOException {
        try (KubernetesClient client = new KubernetesClientBuilder().build()) {
            client.pods().list().getItems().forEach(
                    pod -> System.out.println(pod.getSpec().getContainers().get(0).getImage())
            );
        }

    }

}
