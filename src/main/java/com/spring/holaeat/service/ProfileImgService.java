package com.spring.holaeat.service;


import com.spring.holaeat.domain.profile.ProfileImg;
import com.spring.holaeat.domain.profile.ProfileImgRepository;
import com.spring.holaeat.domain.profile.ProfileImgRequestDto;
import com.spring.holaeat.domain.user.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;


@Service
public class ProfileImgService {

    private final ProfileImgRepository profileImgRepository;

    private  final UserService userService;

    @Autowired
    public ProfileImgService(ProfileImgRepository profileImgRepository, UserService userService) {
        this.profileImgRepository = profileImgRepository;
        this.userService=userService;
    }

    //프로필 자동 생성
    public void createProfile(ProfileImgRequestDto profileImgDto){
        ProfileImg profileImg=new ProfileImg(profileImgDto);
        profileImgRepository.save(profileImg);

    }


    //삭제
    @Transactional
    public void deleteProfile(ProfileImg profileImg){
        profileImgRepository.delete(profileImg);
    }

    //프로필 수정
//    public void uploadProfileImage(User user, ProfileImgRequestDto profileImgDto) throws IOException {
//
//        ProfileImg profileImg = profileImgRepository.findByUser(user);
//        if (profileImg == null) {
//            profileImg = new ProfileImg(profileImgDto); // 프로필 이미지 생성
//        } else {
//            profileImg.update(profileImgDto); // 프로필 이미지 업데이트
//        }
//        profileImgRepository.save(profileImg);
//    }



}





