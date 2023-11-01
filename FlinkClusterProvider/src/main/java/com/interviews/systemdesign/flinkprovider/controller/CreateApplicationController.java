package com.interviews.systemdesign.flinkprovider.controller;


import com.interviews.systemdesign.flinkprovider.model.CreateApplicationRequest;
import com.interviews.systemdesign.flinkprovider.model.CreateApplicationStatus;
import com.interviews.systemdesign.flinkprovider.service.CreateApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/application")
@Slf4j
public class CreateApplicationController {

    private CreateApplicationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateApplicationStatus createApplication(@RequestBody CreateApplicationRequest request) throws IOException {
        return service.createFlinkDeployment(request);
    }
}
