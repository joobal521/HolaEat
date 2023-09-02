package com.spring.holaeat.domain.health;

import com.spring.holaeat.domain.admin.Admin;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor

public class HealthRequestDto {
    private  long healthNo;
    private Admin admin;
    private String title; //제목
    private String content;//내용
   // private MultipartFile img;


    @Builder
    public HealthRequestDto(Admin admin, String title, String content) {
        this.admin = admin;
        this.title = title;
        this.content = content;
    }




}
