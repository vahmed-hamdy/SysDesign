package com.interviews.systemdesign.flinkprovider.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateApplicationRequest implements Serializable {

    private String name;
    private Integer parallelism;
    // Ignored for V0
    private String jarLocation;
}
