package com.spring.holaeat.domain.profile;

import com.spring.holaeat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImgRepository extends JpaRepository<ProfileImg, String> {

    ProfileImg findByUserId(String userId);



}
