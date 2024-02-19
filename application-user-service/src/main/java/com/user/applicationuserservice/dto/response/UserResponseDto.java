package com.user.applicationuserservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private String email;
    private String name;
    private String userId;

    private List<OrderResponseDto> orders;

    @Builder
    public UserResponseDto(String email, String name, String userId, List<OrderResponseDto> orders) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.orders = orders;
    }
}
