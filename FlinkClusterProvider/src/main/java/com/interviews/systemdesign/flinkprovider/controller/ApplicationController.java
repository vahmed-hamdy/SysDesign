package com.interviews.systemdesign.flinkprovider.controller;


import com.interviews.systemdesign.flinkprovider.model.Application;
import com.interviews.systemdesign.flinkprovider.model.CreateApplicationRequest;
import com.interviews.systemdesign.flinkprovider.model.ApplicationStatus;
import com.interviews.systemdesign.flinkprovider.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/application")
@Slf4j
public class ApplicationController {

    private ApplicationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationStatus createApplication(@RequestBody CreateApplicationRequest request) throws IOException {
        return service.CreateFlinkApplication(request);
    }

    @GetMapping(value = "/{appId}")
    @ResponseStatus(HttpStatus.OK)
    public Application getApplication(@PathVariable String appId) {
        return service.getApplication(appId);
    }

    @DeleteMapping(value = "/{appId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteApplication(@PathVariable String appId) throws IOException {
        service.deleteApplication(appId);
    }
}
