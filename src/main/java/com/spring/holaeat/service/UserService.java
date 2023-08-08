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
//     private final UserRepository userRepository;
//
//     public List<User> findByIdAndEmail(String userId, String userEmail) {
//          return userRepository.findAllByUserIdAndUserEmail(userId, userEmail);
//     }
//
//     public List<User> getUserAll() {
//          return userRepository.findAll();
//     }
//
//     public void createUser(UserRequestDto userDto) {
//          User user = new User(userDto);
//          userRepository.save(user);
//     }

}