package com.user.applicationuserservice.service;

import com.user.applicationuserservice.domain.UserEntity;
import com.user.applicationuserservice.dto.request.UserRequestDto;
import com.user.applicationuserservice.dto.response.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserResponseDto createUser(UserRequestDto userRequestDto);
    List<UserResponseDto> getUsers();
    UserResponseDto getUserByUserId(String userId);
    UserEntity getUserByEmail(String email);
}
