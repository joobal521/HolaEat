package com.spring.holaeat.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class UserRequestDto {

    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private byte[]userProfileImg;



}
