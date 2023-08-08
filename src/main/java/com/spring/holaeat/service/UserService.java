package com.spring.holaeat.service;

import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.domain.user.UserRequestDto;
import com.spring.holaeat.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {
     private  final UserRepository userRepository;

     public List<User>findByIdAndEmail(String userId, String userEmail){
          return (List<User>) userRepository.findAllByUserIdAndUserEmail(userId,userEmail);
     }

     public List<User> getUserAll(){
          List<User> list= userRepository.findAll();
          return list;
     }

     public User getUserById(String user_id){
          User user=userRepository.findById(user_id).orElseThrow(
                  ()->new IllegalArgumentException("존재하지 않는 사용자입니다.")
          );
          return user;

     }

     //회원가입
     public void createUser(UserRequestDto userDto){
          User user=new User(userDto);
          userRepository.save(user);

     }

     //회원탈퇴
     @Transactional
     public void deleteUserById(String userId){
          userRepository.deleteById(userId);
     }

     //회원정보 수정
     @Transactional
     public void updateUser(String userId, UserRequestDto userDto){
          User user=getUserById(userId);
          user.update(userDto);

     }









}