package com.user.applicationuserservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, unique = true)
    private String encryptPwd;

    //TODO : BaseEntity 예정
//    @CreatedDate
//    private LocalDate createdAt;
//    @CreatedBy
//    private String createdBy;
//    @LastModifiedDate
//    private LocalDate modifiedAt;
//    @LastModifiedBy
//    private String modifiedBy;

    @Builder
    public UserEntity(String email, String name, String userId, String encryptPwd) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.encryptPwd = encryptPwd;
    }
}
