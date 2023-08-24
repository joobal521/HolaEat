package com.spring.holaeat.domain.user_menu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMenuRepository extends JpaRepository<UserMenu, Long> {
    List<UserMenu> findByUserId(String userId);


}
