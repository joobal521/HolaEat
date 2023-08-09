package com.spring.holaeat.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class UserRequestDto {

    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private byte[] userProfileImg;



}
