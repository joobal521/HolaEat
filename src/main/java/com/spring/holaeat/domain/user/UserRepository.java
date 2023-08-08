package com.spring.holaeat.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    public User findAllByUserIdAndUserEmail(String userId, String userEmail);

    //2
    //SELECT *FROM users WHERE email LIKE ?
    //public List<User> findAllByUser_emailLikeOrderByUser_name(String pattern);



}
