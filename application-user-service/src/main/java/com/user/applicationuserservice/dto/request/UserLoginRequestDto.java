package com.user.applicationuserservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDto {
    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than two character")
    @Email
    private String email;

    @NotNull(message = "password cannot be null")
    @Size(min = 8, message = "password must be equals or grater than two character")
    private String password;
}
