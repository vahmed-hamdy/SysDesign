package com.interviews.systemdesign.flinkprovider.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@NoArgsConstructor
public class IdGenerator {
    private final AtomicInteger sequencer = new AtomicInteger(1);

    public String getIdForApp(String name) {
        return name;
    }
}
