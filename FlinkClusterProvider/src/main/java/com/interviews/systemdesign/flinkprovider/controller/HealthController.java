package com.interviews.systemdesign.flinkprovider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

@RestController
@RequestMapping("/healthz")
@Slf4j
public class HealthController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void announceHealthy(){
        log.debug("I am fine");
    }

    public static void main(String args[]) throws Exception {
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "password");
//        props.setProperty("ssl", "false");
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://vvc-db.cxta5ciadexg.eu-central-1.rds.amazonaws.com:5432/vvc_db", props)) {
            try (Statement statement = connection.createStatement()) {
//                statement.execute("CREATE TABLE T(\n" +
//                        "  a INT,\n" +
//                        "  b VARCHAR(10)\n" +
//                        ") WITH (\n" +
//                        "  'connector' = 'filesystem',\n" +
//                        "  'path' = 'file:///tmp/T.csv',\n" +
//                        "  'format' = 'csv'\n" +
//                        ")");
//                statement.execute("INSERT INTO T VALUES (1, 'Hi'), (2, 'Hello')");
                try (ResultSet rs = statement.executeQuery("SELECT * FROM vvc_source;")) {
                    while (rs.next()) {
                        System.out.println(rs.getString(1) + ", " + rs.getString(2));
                    }
                }
            }
        }
    }
}
