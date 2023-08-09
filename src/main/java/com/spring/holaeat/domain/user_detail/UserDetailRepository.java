package com.spring.holaeat.domain.user_detail;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDetailRepository extends JpaRepository<UserDetail, String> {
    UserDetail findByUserId(String userId);

}
