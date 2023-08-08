package com.spring.holaeat.domain.board;


import com.spring.holaeat.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class BoardRequestDto {

    private long reviewNo;
    private String title;
    private String content;
    private String userId;
    private MultipartFile img;


}
