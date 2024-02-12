package com.example.gatewayfirstservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/first-service")
@RestController
@Slf4j
@RequiredArgsConstructor
public class Controller {
    private final Environment env;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to My FirstService Application";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);
        return "Hello World in First Service";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("first service server port : ${}",request.getLocalPort());
        return "Hello World in First Service"+env.getProperty("local.server.port");
    }
}
