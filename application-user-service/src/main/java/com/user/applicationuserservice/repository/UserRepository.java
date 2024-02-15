package com.user.applicationuserservice.repository;

import com.user.applicationuserservice.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
