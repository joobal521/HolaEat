package com.spring.holaeat.service;

import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.domain.user.UserRequestDto;
import com.spring.holaeat.domain.user.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {
     private  final UserRespository userRespository;

     public List<User>findByIdAndEmail(String user_id, String user_email){
          return (List<User>) userRespository.findAllByUser_idAndUser_email(user_id, user_email);
     }

     public List<User> getUserAll(){
          List<User> list= userRespository.findAll();
          return list;
     }

     public void createUser(UserRequestDto userDto){
          User user=new User(userDto);
          userRespository.save(user);

     }






}
