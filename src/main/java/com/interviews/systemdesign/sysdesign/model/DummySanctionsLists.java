package com.interviews.systemdesign.sysdesign.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "sanction")
public class DummySanctionsLists {
    private final List<String> sanctionList;

    public boolean isSanctioned(String name) {
        return sanctionList.contains(name);
    }
}
