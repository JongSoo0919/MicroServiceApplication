package com.user.applicationuserservice.service.impl;

import com.user.applicationuserservice.domain.UserEntity;
import com.user.applicationuserservice.dto.CustomUserDetails;
import com.user.applicationuserservice.dto.request.UserRequestDto;
import com.user.applicationuserservice.dto.response.UserResponseDto;
import com.user.applicationuserservice.repository.UserRepository;
import com.user.applicationuserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        UserEntity user = userRepository.save(UserEntity.builder()
                .email(userRequestDto.getEmail())
                .name(userRequestDto.getName())
                .userId(UUID.randomUUID().toString())
                .encryptPwd(bCryptPasswordEncoder.encode(userRequestDto.getPwd()))
                .build());

        return UserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .userId(user.getUserId())
                .build();
    }

    @Override
    public List<UserResponseDto> getUsers() {
        Iterable<UserEntity> users = userRepository.findAll();
        List<UserResponseDto> result = new ArrayList<>();
        users.forEach(user -> {
            result.add(UserResponseDto.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .userId(user.getUserId())
                    .orders(List.of())
                    .build());
        });
        return result;
    }

    @Override
    public UserResponseDto getUserByUserId(String userId) {
        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("유저가 존재 하지 않습니다."));

        return UserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .userId(user.getUserId())
                .orders(List.of())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("유저가 존재 하지 않습니다."));

        return new CustomUserDetails(user);
    }
}

