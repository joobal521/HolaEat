package com.spring.holaeat.domain.health_board;

import com.spring.holaeat.domain.admin.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor

public class HealthBoardRequestDto {
    private  long healthNo;
    private String title; //제목
    private String content;//내용
    private Admin admin;
  //  private MultipartFile img;

}
