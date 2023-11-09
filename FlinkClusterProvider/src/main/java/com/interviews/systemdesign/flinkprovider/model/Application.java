package com.interviews.systemdesign.flinkprovider.model;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document(collection = "applications")
@Data
@AllArgsConstructor
@Builder
public class Application {
    @Id
    private final String id;

    @Indexed
    private String name;

    private int parallelism;

//    @Field("jar-location")
    private String jarLocation;

    private ApplicationStatus status;

    @Nullable
    private String cluster;
}
