package com.spring.holaeat.domain.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailConfigRequestDto {
    private String host;
    private int port;
    private String username;


    private String password;
    private boolean auth;
    private boolean starttlsEnable;


    private boolean starttlsRequired;
    private int connectionTimeout;
    private int timeout;
    private int writeTimeout;
}
