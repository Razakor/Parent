package com.razakor.task.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.razakor.task.mongorepositories")
@EntityScan(basePackages = "com.razakor.task.documents")
@SpringBootApplication
public class ParserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);
    }
}
