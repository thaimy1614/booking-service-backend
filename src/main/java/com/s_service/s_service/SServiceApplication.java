package com.s_service.s_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
public class SServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SServiceApplication.class, args);
    }

}
