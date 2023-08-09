package com.spring.holaeat.domain.review;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class ReviewRequestDto {

    private long reviewNo;
    private String title;
    private String content;
    private String userId;
    private MultipartFile img;


}
