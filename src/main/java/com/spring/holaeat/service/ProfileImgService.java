package com.spring.holaeat.service;


import com.spring.holaeat.domain.profile.ProfileImg;
import com.spring.holaeat.domain.profile.ProfileImgRepository;
import com.spring.holaeat.domain.profile.ProfileImgRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;


@Service
public class ProfileImgService {

    private final ProfileImgRepository profileImgRepository;

    @Autowired
    public ProfileImgService(ProfileImgRepository profileImgRepository) {
        this.profileImgRepository = profileImgRepository;
    }

    public void uploadProfileImage(String userId, ProfileImgRequestDto profileImgDto) throws IOException {
        ProfileImg profileImg = profileImgRepository.findByUserId(userId);

        profileImg.update(profileImgDto);
        profileImgRepository.save(profileImg);
    }



}





