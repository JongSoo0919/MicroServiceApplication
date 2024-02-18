package com.user.applicationuserservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//TODO : status return
public class UserResponseDto {
    private String email;
    private String name;
    private String userId;

    @Builder
    public UserResponseDto(String email, String name, String userId) {
        this.email = email;
        this.name = name;
        this.userId = userId;
    }
}
