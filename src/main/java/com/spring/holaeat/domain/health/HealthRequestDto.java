package com.spring.holaeat.domain.health;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor

public class HealthRequestDto {
    private  long healthNo;
    private String title; //제목
    private String content;//내용
    private MultipartFile img;

}
