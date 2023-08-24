package com.spring.holaeat.controller;

import com.spring.holaeat.domain.health.Health;
import com.spring.holaeat.domain.profile.ProfileImg;
import com.spring.holaeat.domain.profile.ProfileImgRepository;
import com.spring.holaeat.domain.profile.ProfileImgRequestDto;
import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRequestDto;
import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.payload.Response;
import com.spring.holaeat.service.ProfileImgService;
import com.spring.holaeat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("api/v1/my")
public class ProfileImgController {

    private final ProfileImgService profileImgService;
    private final ProfileImgRepository profileImgRepository;

    // private final UserService userService;


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


