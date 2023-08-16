package com.spring.holaeat.domain.profile;

import com.spring.holaeat.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class ProfileImgRequestDto {
    private Long profileNo;
    private MultipartFile profileImg;



}
