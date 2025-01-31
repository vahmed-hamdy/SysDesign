package com.interviews.systemdesign.flinkprovider.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class CreateClusterRequest {
    private String id;

    @Builder.Default
    private String cluster = "minikube";

    @Builder.Default
    private String namespace = "default";

}
