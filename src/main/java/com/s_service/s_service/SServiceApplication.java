package com.s_service.s_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SServiceApplication.class, args);
    }

}
