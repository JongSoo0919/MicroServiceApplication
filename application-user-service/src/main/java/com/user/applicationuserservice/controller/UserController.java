package com.user.applicationuserservice.controller;

import com.user.applicationuserservice.constants.Constants;
import com.user.applicationuserservice.dto.request.UserRequestDto;
import com.user.applicationuserservice.dto.response.UserResponseDto;
import com.user.applicationuserservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Environment env;
    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service"
                + ", port(local.server.port) = " + env.getProperty("local.server.port")
                + ", port(server.port) = " + env.getProperty("server.port")
                + ", token secret = " + env.getProperty("token.secret")
                + ", token expiration_time = " + env.getProperty("token.expiration_time");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return Constants.WELCOME_MESSAGE;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(
            @PathVariable String userId
    ) {
        return ResponseEntity.ok().body(userService.getUserByUserId(userId));
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        //TODO : ExceptionHandler
        //TODO : Swagger
        return ResponseEntity.status(201).body(userService.createUser(userRequestDto));
    }

}
