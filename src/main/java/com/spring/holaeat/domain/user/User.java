package com.spring.holaeat.domain.user;

import com.spring.holaeat.util.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Table(name = "users")
@Entity
public class User extends Timestamp {


    @Id
    private String user_id;
    private String user_password;
    private String user_name;
    @Column(unique = true)
    private String user_email;
    private byte[]user_profile_img;







    //생성자
    public User(UserRequestDto userDto) {
        this.user_id = userDto.getUser_id();
        this.user_password = userDto.getUser_password();
        this.user_name = userDto.getUser_name();
        this.user_email = userDto.getUser_email();
        this.user_profile_img = userDto.getUser_profile_img();
    }











}
