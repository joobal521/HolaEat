package com.spring.holaeat.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
//   수정된 메소드명과 반환 타입
  public List<User> findAllByUserIdAndUserEmail(String userId, String userEmail);
    User findByUserId(String userId); //


  Optional<User> findByUserEmail(String userEmail);







}
