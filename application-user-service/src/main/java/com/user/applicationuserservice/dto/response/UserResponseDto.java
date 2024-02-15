package com.user.applicationuserservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//TODO : status return
public class UserResponseDto {
    private String message;

    @Builder
    public UserResponseDto(String message) {
        this.message = message;
    }
}
