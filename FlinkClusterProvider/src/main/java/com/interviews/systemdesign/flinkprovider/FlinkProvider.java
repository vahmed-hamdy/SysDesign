package com.interviews.systemdesign.flinkprovider;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FlinkProvider {
    public static void main(String[] args) {
        SpringApplication.run(FlinkProvider.class, args);
    }

}
