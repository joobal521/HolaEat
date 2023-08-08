package com.spring.holaeat.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class UserRequestDto {

    private String user_id;
    private String user_password;
    private String user_name;
    private String user_email;
    private byte[]user_profile_img;



}
