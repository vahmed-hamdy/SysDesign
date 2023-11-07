package com.interviews.systemdesign.flinkprovider.model;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("clusters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cluster {
    @Id
    private String id;
    private String cluster;
    @Builder.Default
    private String namespace = "default";
    private ClusterStatus status;
    @Nullable
    private String applicationId;
}
