package com.user.applicationuserservice.service.impl;

import com.user.applicationuserservice.client.OrderServiceClient;
import com.user.applicationuserservice.domain.UserEntity;
import com.user.applicationuserservice.dto.CustomUserDetails;
import com.user.applicationuserservice.dto.request.UserRequestDto;
import com.user.applicationuserservice.dto.response.OrderResponseDto;
import com.user.applicationuserservice.dto.response.UserResponseDto;
import com.user.applicationuserservice.repository.UserRepository;
import com.user.applicationuserservice.service.UserService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final OrderServiceClient orderServiceClient;
//    private final RestTemplate restTemplate;
//    private final Environment env;

    private final CircuitBreakerFactory circuitBreakerFactory;
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        // 중복 회원가입 처리
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }

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

//        String orderUrl = String.format(env.getProperty("order_service.url"), userId);
//        ResponseEntity<List<OrderResponseDto>> listResponseEntity
//                = restTemplate.exchange(orderUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderResponseDto>>() {});
//        List<OrderResponseDto> ordersList = listResponseEntity.getBody();

        /* Feign Exception Handling */
//        List<OrderResponseDto> ordersList = orderServiceClient.getOrders(userId);

        /* circuitbreaker */
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        List<OrderResponseDto> ordersList = circuitBreaker.run(() -> orderServiceClient.getOrders(userId)
                , throwable -> new ArrayList<>());

        return UserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .userId(user.getUserId())
                .orders(ordersList)
                .build();
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("유저가 존재 하지 않습니다."));

        return new CustomUserDetails(user);
    }
}

