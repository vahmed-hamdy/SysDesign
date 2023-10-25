package com.interviews.systemdesign.sysdesign.controller;

import com.interviews.systemdesign.sysdesign.model.Pair;
import com.interviews.systemdesign.sysdesign.service.DummyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dummy")
public class DummyController {
    private final DummyService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pair> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{key}")
    public Long getValForKey(@PathVariable String key) {
        return service.getOne(key);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pair getValForKey(@RequestBody Pair newPair) {
        return service.addSomething(newPair);
    }
}
