package com.spring.holaeat.domain.health;

import com.spring.holaeat.domain.admin.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class HealthRequestDto {
    private  long healthNo;
    private String title; //제목
    private String content;//내용
    private Admin admin;
  //  private MultipartFile img;

}
