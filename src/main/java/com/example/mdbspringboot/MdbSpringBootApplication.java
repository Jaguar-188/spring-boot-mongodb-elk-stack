package com.example.mdbspringboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableCaching
public class MdbSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdbSpringBootApplication.class, args);
    }

}
