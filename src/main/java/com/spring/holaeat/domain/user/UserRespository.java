package com.spring.holaeat.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRespository extends JpaRepository<User,String> {

    public User  findAllByUser_idAndUser_email(String user_id, String user_email);

    //2
    //SELECT *FROM users WHERE email LIKE ?
    public List<User> findAllByUser_emailLikeOrderByUser_name(String pattern);



}
