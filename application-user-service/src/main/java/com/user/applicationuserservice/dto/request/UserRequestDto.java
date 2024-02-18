package com.user.applicationuserservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 2, message = "Name not be less than two character")
    private String name;

    @NotNull
    @Size(min = 8, message = "Password must be equal or grater than 8 character")
    private String pwd;

//    private String userId;
//    private LocalDate createdAt;
//    private String encryptPwd;
}
