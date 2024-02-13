package com.user.applicationuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationUserServiceApplication.class, args);
    }

}
