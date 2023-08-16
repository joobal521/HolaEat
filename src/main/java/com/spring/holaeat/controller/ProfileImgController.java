package com.spring.holaeat.controller;

import com.spring.holaeat.domain.profile.ProfileImg;
import com.spring.holaeat.domain.profile.ProfileImgRepository;
import com.spring.holaeat.domain.profile.ProfileImgRequestDto;
import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.service.ProfileImgService;
import com.spring.holaeat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("api/v1/my")
public class ProfileImgController {

    private final ProfileImgService profileImgService;
    //private final UserService userService;


    @Autowired
    public ProfileImgController(ProfileImgService profileImgService) {
        this.profileImgService = profileImgService;
    }




    @PutMapping("profile")
    public Map<String, Object> uploadProfile(
            @RequestParam String userId,
            @RequestParam MultipartFile profileImg) throws IOException {
        JSONObject response = new JSONObject();

        try {
            ProfileImgRequestDto profileImgDto = new ProfileImgRequestDto();

            profileImgDto.setProfileImg(profileImg);

            profileImgService.uploadProfileImage(userId, profileImgDto);

            response.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("result", false);
            response.put("message", "프로필 업로드 실패");
        }

        return response.toMap();
    }
}


