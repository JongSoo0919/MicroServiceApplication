package com.example.gatewaysecondservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/second-service")
@RestController
public class Controller {
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to My Second Application";
    }
}
