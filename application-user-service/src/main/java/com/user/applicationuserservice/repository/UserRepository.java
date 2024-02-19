package com.user.applicationuserservice.repository;

import com.user.applicationuserservice.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);
}
