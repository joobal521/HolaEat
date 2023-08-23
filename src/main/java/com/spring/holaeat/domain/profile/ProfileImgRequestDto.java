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
    private String userId;
    private MultipartFile profileImg;

//    public ProfileImgRequestDto(Long profileNo, String userId, MultipartFile profileImg) {
//        this.profileNo = profileNo;
//        this.userId = userId;
//        this.profileImg = profileImg;
//    }


}
