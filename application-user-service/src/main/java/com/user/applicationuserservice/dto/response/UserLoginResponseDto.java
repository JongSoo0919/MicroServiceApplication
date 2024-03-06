package com.user.applicationuserservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponseDto {
    private String accessToken;
    private String userId;

    @Builder
    public UserLoginResponseDto(String accessToken, String userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }
}
