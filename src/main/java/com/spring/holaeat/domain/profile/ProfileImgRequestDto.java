package com.spring.holaeat.domain.profile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class ProfileImgRequestDto {
    private long profileNo;
    private String userId;
    private MultipartFile profileImg;




}
