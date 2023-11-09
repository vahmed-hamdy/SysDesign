package com.interviews.systemdesign.flinkprovider.controller;

import com.interviews.systemdesign.flinkprovider.model.CreateClusterRequest;
import com.interviews.systemdesign.flinkprovider.service.ClusterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/cluster")
@AllArgsConstructor
public class ClusterController {

    private final ClusterService clusterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCluster(@RequestBody CreateClusterRequest request) throws IOException {
        clusterService.createCluster(request);
    }
}
