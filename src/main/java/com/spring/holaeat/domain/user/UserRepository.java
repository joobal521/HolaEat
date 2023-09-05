package com.spring.holaeat.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
//   수정된 메소드명과 반환 타입
  public List<User> findAllByUserIdAndUserEmail(String userId, String userEmail);
  public  User findByUserId(String userId); //


 public Optional<User> findByUserEmail(String userEmail);

 public User findByUserPassword(String userPassword);

 public User findByUserEmailAndUserName(String userEmail, String userId);

//게시판으로 userId 프로필 이미지 가져오기
//byte[] findUserProfileImgByUserId(String userId);





}
