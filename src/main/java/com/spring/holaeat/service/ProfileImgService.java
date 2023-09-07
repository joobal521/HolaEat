package com.spring.holaeat.service;


import com.spring.holaeat.domain.profile.ProfileImg;
import com.spring.holaeat.domain.profile.ProfileImgRepository;
import com.spring.holaeat.domain.profile.ProfileImgRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


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
    public void createProfile(ProfileImgRequestDto profileImgDto, String userId){
        ProfileImg profileImg=new ProfileImg(profileImgDto, userId);
        profileImgRepository.save(profileImg);

    }


    //부모 테이블 삭제전 자식 테이블 삭제
    //삭제
    @Transactional
    public void deleteProfile(String userId){

        profileImgRepository.deleteByUserId(userId);
    }

    //프로필 수정
    @Transactional
    public void uploadProfileImage(ProfileImg profileImg, ProfileImgRequestDto profileImgDto) {

        profileImg.update(profileImgDto);
    }


    //유저 프로필 이미지 출력
     public byte[] findUserProfileImgByUserId(String userId){
          return profileImgRepository.findUserProfileImgByUserId(userId);

     }



}





