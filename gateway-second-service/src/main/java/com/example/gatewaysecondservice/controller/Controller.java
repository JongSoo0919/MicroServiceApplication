package com.example.gatewaysecondservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/second-service")
@RestController
@Slf4j
@RequiredArgsConstructor
public class Controller {
    private final Environment env;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to My Second Application";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        log.info(header);
        return "Hello World in Second Service";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("my second service port : {}",request.getServerPort());
        return "Hello World in Second Service" + env.getProperty("local.server.port");
    }
}
