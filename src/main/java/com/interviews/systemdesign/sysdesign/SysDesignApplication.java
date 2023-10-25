package com.interviews.systemdesign.sysdesign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SysDesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysDesignApplication.class, args);
    }

}
