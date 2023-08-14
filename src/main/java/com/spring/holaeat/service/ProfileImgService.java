package com.spring.holaeat.service;


import com.spring.holaeat.domain.profile.ProfileImg;
import com.spring.holaeat.domain.profile.ProfileImgRepository;
import com.spring.holaeat.domain.profile.ProfileImgRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class ProfileImgService {

    private ProfileImgRepository profileImgRepository;


    //프로필 이미지 수정
    @Transactional
     public void uploadProfileImage(String userId, ProfileImgRequestDto profileImgDto) {
         ProfileImg profileImg = profileImgRepository.findByUserId(userId);

          if (profileImg != null) {
              profileImg.update(profileImgDto);

          }else{
              System.out.println("기본 이미지로 설정");

              profileImgRepository.save(profileImg);
          }



}




}
