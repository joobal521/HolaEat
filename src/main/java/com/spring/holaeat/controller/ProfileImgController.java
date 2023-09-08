package com.spring.holaeat.controller;

import com.spring.holaeat.domain.profile.ProfileImg;
import com.spring.holaeat.domain.profile.ProfileImgRepository;
import com.spring.holaeat.domain.profile.ProfileImgRequestDto;
import com.spring.holaeat.payload.Response;
import com.spring.holaeat.service.ProfileImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;


@RestController
public class ProfileImgController {

    private final ProfileImgService profileImgService;
    private final ProfileImgRepository profileImgRepository;



    @Autowired
    public ProfileImgController(ProfileImgService profileImgService,ProfileImgRepository profileImgRepository) {
        this.profileImgService = profileImgService;
        this.profileImgRepository=profileImgRepository;
    }

    @PostMapping(value = "/profile/{profileNo}", consumes = {"multipart/form-data"})
    public Response uploadProfile(@PathVariable long profileNo, @ModelAttribute ProfileImgRequestDto profileImgDto, HttpSession session) throws IOException {

        ProfileImg profileImg=profileImgRepository.findById(profileNo).orElseThrow(
                ()->new IllegalArgumentException("프로필 이미지가 존재하지 않습니다.")
        );

        if (profileImgDto.getProfileImg() != null) {
            profileImgService.uploadProfileImage(profileImg, profileImgDto);
            session.setAttribute("profileImg", profileImg.getProfileImg());
        } else {
            System.out.println("null이면?");
        }
        return new Response("update","success");
    }




}


