package com.user.applicationuserservice.service.impl;

import com.user.applicationuserservice.domain.UserEntity;
import com.user.applicationuserservice.dto.request.UserRequestDto;
import com.user.applicationuserservice.dto.response.UserResponseDto;
import com.user.applicationuserservice.repository.UserRepository;
import com.user.applicationuserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        UserEntity user = userRepository.save(UserEntity.builder()
                .email(userRequestDto.getEmail())
                .name(userRequestDto.getName())
                .userId(UUID.randomUUID().toString())
                .encryptPwd("Encrypted_password") // TODO : 구현 예정
                .build());

        return UserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .userId(user.getUserId())
                .build();
    }
}

