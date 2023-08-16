package com.spring.holaeat.domain.profile;

import com.spring.holaeat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImgRepository extends JpaRepository<ProfileImg, Long> {

    //유저별 이미지를 찾아야한다
    ProfileImg findByUser(User user);



}
