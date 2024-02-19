package com.user.applicationuserservice.service;

import com.user.applicationuserservice.dto.request.UserRequestDto;
import com.user.applicationuserservice.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);
    List<UserResponseDto> getUsers();
    UserResponseDto getUserByUserId(String userId);
}
